package offer;

import java.util.Stack;

/**
 * @author lvgang
 * @date 2020/12/11 14:36
 */
public class MinStack {
    private Stack<Integer> a, b;
    public MinStack() {
        a = new Stack<Integer>();
        b = new Stack<Integer>();
    }

    public void push(int x) {
        a.push(x);
        if (b.empty() || x <= b.peek()) {
            b.push(x);
        }
    }

    public void pop() {
        if (a.pop().equals(b.peek())) {
            b.pop();
        }
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        return b.peek();
    }
}
