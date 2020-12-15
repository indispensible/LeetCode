package offer;

/**
 * @author lvgang
 * @date 2020/12/7 14:06
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >>> 1;
        }
        return count;
    }
}
