package offer;

import java.util.*;

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

    public String longestPalindrome(String s) {
        String longest = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i < s.length() - l; i++) {
                int j = i + l;
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = b && dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && (j - i + 1) > longest.length()) {
                    longest = s.substring(i, j + 1);
                }
            }
        }
        return longest;
    }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (j == 1) {
                        if (i == 0) {
                            dp[i][j] = true;
                        }
                        dp[i][j] = false;
                        continue;
                    }
                    dp[i][j] = dp[i][j - 2];
                    if (i > 0) {
                        if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                            dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                        }
                    }
                } else {
                    dp[i][j] = false;
                    if (i != 0) {
                        dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                    }
                }
            }
        }
        return dp[m][n];
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>(16);
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list =  map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public String minWindow4tNotDuplicate(String s, String t) {
        int l = Integer.MAX_VALUE - 1, r = -1, minL = Integer.MAX_VALUE - 1, minR = -1;
        Map<Character, Integer> map = new HashMap<>(64);
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), -1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                l = Integer.MAX_VALUE;
                r = i;
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() < l) {
                        l = entry.getValue();
                    }
                }
                if (l >= 0 && r - l < Math.abs(minR - minL)) {
                    minL = l;
                    minR = r;
                }
            }
        }
        if (minL >= 0 && minR >= 0 && minR >= minL) {
            return s.substring(minL, minR + 1);
        }
        return "";
    }

    Map<Character, Integer> map = new HashMap<>(64);

    public String minWindow(String s, String t) {
        int l = 0, r = 0;
        String res = "", tmp = "";
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                map.put(s.charAt(r), map.get(s.charAt(r)) - 1);
                while (check() && l <= r) {
                    tmp = s.substring(l, r + 1);
                    if (tmp.length() < res.length() || res.equals("")) {
                        res = tmp;
                    }
                    if (map.containsKey(s.charAt(l))) {
                        map.put(s.charAt(l), map.get(s.charAt(l)) + 1);
                    }
                    l++;
                }
            }
        }
        return res;
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        DealString dealString = new DealString();

        System.out.println(dealString.minWindow("ADOBECODEBANC", "ABCA"));

//        System.out.println(dealString.isMatch("mississippi", "mis*is*ip*."));

//        String[] strs = {"eat","tea","tan","ate","nat","bat"};
//        dealString.groupAnagrams(strs);

//        dealString.longestPalindrome("babad");

//        dealString.reverseWords("a good   example");
//        String[] a = dealString.permutation("abc");
//        System.out.println(a);

//        System.out.println(dealString.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(dealString.lengthOfLongestSubstring("dvdf"));
//        System.out.println(dealString.lengthOfLongestSubstring("aab"));
//        System.out.println(dealString.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(dealString.lengthOfLongestSubstring("bbbbbb"));
    }
}
