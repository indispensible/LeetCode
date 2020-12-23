package offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author lvgang
 * @date 2020/12/23 12:21
 */
public class DealChar {
    public char firstUniqChar(String s) {
        Map<Character, Integer> hashMap = new HashMap<>(16);
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = s.toCharArray();
        char first = ' ';
        int min = 60000;
        for (int i = 0; i < chars.length; i++) {
            if (hashSet.contains(chars[i])) {
                continue;
            }
            if (hashMap.containsKey(chars[i])) {
                hashMap.remove(chars[i]);
                hashSet.add(chars[i]);
            } else {
                hashMap.put(chars[i], i);
            }
        }
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                first = entry.getKey();
            }
        }
        return first;
    }
}
