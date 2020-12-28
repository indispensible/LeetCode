package offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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

    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new LinkedList<>();
        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (queue.contains(chars[i])) {
                max = Math.max(max, queue.size());
                while (queue.contains(chars[i])) {
                    queue.poll();
                }
            }
            queue.add(chars[i]);
        }
        max = Math.max(max, queue.size());
        return max;
    }

    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        s = s.trim();
        for (String str : s.split(" ")) {
            if (!str.equals("")) {
                stringBuilder.insert(0, str.trim() + " ");
            }
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        DealString dealString = new DealString();
        dealString.reverseWords("a good   example");
//        String[] a = dealString.permutation("abc");
//        System.out.println(a);

//        System.out.println(dealString.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(dealString.lengthOfLongestSubstring("dvdf"));
//        System.out.println(dealString.lengthOfLongestSubstring("aab"));
//        System.out.println(dealString.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(dealString.lengthOfLongestSubstring("bbbbbb"));
    }
}
