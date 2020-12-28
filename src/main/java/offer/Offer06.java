package offer;

import java.util.Stack;

/**
 * @author lvgang
 * @date 2020/11/28 16:28
 */


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

/**
 * @author lvxiaoxiang
 */
public class Offer06 {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        int size = stack.size();
        int[] arrays = new int[size];
        for (int i = 0; i < size; i++) {
            arrays[i] = stack.pop();
        }
        return arrays;
    }
}
