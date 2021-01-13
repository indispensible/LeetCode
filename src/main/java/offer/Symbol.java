package offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lvgang
 * @date 2021/1/7 12:55
 */
public class Symbol {
    StringBuilder s = new StringBuilder();
    List<String> lists = new ArrayList<>();
    Stack<Character> stack = new Stack<>();
    int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        if (n > 0) {
            recur(0);
        }
        return lists;
    }

    public void recur(int count) {
        if (count == n) {
            StringBuilder cur = new StringBuilder(s.toString());
            for (int i = 0; i < stack.size(); i++) {
                cur.append(')');
            }
            lists.add(cur.toString());
            return;
        }
        stack.push('(');
        s.append('(');
        recur(count + 1);
        stack.pop();
        s.deleteCharAt(s.length() - 1);
        int length = stack.size();
        for (int i = 1; i <= length; i++) {
            stack.pop();
            for (int j = 0; j < i; j++) {
                s.append(')');
            }
            stack.push('(');
            s.append('(');
            recur(count + 1);
            stack.pop();
            s.delete(s.length() - 1 - i, s.length());
        }
        for (int i = 1; i <= length; i++) {
            stack.push('(');
        }
    }

    public static void main(String[] args) {
        Symbol symbol = new Symbol();
        symbol.generateParenthesis(3);
    }
}
