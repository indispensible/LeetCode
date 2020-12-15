package offer;

/**
 * @author lvgang
 * @date 2020/12/9 9:58
 */
public class Match {

    private char star = '*';
    private char point = '.';

    public boolean isMatch(String s, String p) {
        boolean judge = false;
        if (s.isEmpty() && p.isEmpty()) {
            return true;
        }
        if (p.isEmpty()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if(pChars.length >= 2) {
            if (pChars[1] == star) {
                judge = isMatch(s, p.substring(2));
                if (!judge) {
                    for (int i = 0; i < sChars.length; i++) {
                        if (sChars[i] == pChars[0] || pChars[0] == point) {
                            judge = isMatch(s.substring(i + 1), p.substring(2));
                            if (judge) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            } else if (sChars.length > 0 && (sChars[0] == pChars[0] || pChars[0] == point)) {
                judge = isMatch(s.substring(1), p.substring(1));
            }
        } else if (sChars.length > 0) {
            if (sChars[0] == pChars[0] || pChars[0] == point) {
                judge = isMatch(s.substring(1), p.substring(1));
            }
        }
        return judge;
    }

    public static void main(String[] args) {
        Match match = new Match();
        System.out.println(match.isMatch("a", ".*..a"));
        System.out.println(match.isMatch("a", "ab*"));
        System.out.println(match.isMatch("sippi", "s*p*."));
        System.out.println(match.isMatch("mississippi", "mis*is*p*."));
        System.out.println(match.isMatch("aa", "a"));
        System.out.println(match.isMatch("aaa", "aa.a"));
        System.out.println(match.isMatch("aaa", "ab*a"));
        System.out.println(match.isMatch("aa", "a*"));
        System.out.println(match.isMatch("ab", ".*"));
        System.out.println(match.isMatch("aab", "c*a*b"));
        System.out.println(match.isMatch("aaa", "ab*ac*a*a"));
        System.out.println(match.isMatch("aaa", "aa.a*"));
    }

}
