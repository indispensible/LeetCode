package offer;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author lvgang
 * @date 2020/12/17 9:51
 */
public class DealString {
    private HashSet<String> hashSet = new HashSet<String>(16);

    public String[] permutation(String s) {
        if ("".equals(s)) {
            return new String[0];
        }
        recur(new StringBuilder(s), new StringBuilder());
        return hashSet.toArray(new String[0]);
    }

    public void recur(StringBuilder s, StringBuilder newSortedString) {
        if (s.length() == 0) {
            hashSet.add(newSortedString.toString());
            return;
        }
        char[] chars = s.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            newSortedString.append(chars[i]);
            s.deleteCharAt(i);
            recur(s, newSortedString);
            s.insert(i, chars[i]);
            newSortedString.deleteCharAt(newSortedString.length() - 1);
        }
    }

    public static void main(String[] args) {
        DealString dealString = new DealString();
        String[] a = dealString.permutation("abc");
        System.out.println(a);
    }
}
