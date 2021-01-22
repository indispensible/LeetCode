package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import org.springframework.data.redis.connection.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvgang
 * @date 2020/11/30 12:49
 * 这个类是可重入锁的原因是，其中加入redis中的key对应的String值是唯一的。第二次赋值同样的key是会把前一个value覆盖掉，
 * 所以为了不覆盖前一个value，采用的策略是在线程中存入一个HashMap，用于存储redis的某个key出现的次数，该key第一次出现
 * 就存入到redis中，后面再出现就把HashMap中的值+1，解锁的话就是删到HashMap中的key对应的value值为0再去从redis中删除该
 * key值，然后这个锁就可以释放了，此时该key就可以赋值新的value了
 */
public class RedisWithReentrantLock {
    private ThreadLocal<Map> lockers = new ThreadLocal<Map>();
    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        SetParams params = new SetParams().nx().ex(5);
        jedis.zrangeByScore("queueKey", 0, System.currentTimeMillis(), 0, 1);
        return jedis.set(key, "", params) != null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map <String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<String, Integer>(16));
        return lockers.get();
    }

    public boolean lock(String key) {
        Map refs = currentLockers();
        Integer refCnt = (Integer) refs.get(key);
        if (refCnt != null) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map refs = currentLockers();
        Integer refCnt = (Integer) refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.pipelined();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
    }
}
