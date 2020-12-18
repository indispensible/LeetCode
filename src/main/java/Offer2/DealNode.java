package Offer2;

import java.util.Stack;

/**
 * @author lvgang
 * @date 2020/12/16 9:49
 */
public class DealNode {
    Stack<Node> stack = new Stack<Node>();
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        recur(root);
        Node head = stack.peek(), pre = stack.pop(), post = null;
        while (!stack.isEmpty()) {
            post = stack.pop();
            pre.right = post;
            post.left = pre;
            pre = post;
        }
        if (post == null) {
            head.left = head;
            head.right = head;
        } else {
            head.left = post;
            post.right = head;
        }
        return head;
    }

    public void recur(Node node) {
        if (node != null) {
            recur(node.right);
            stack.add(node);
            recur(node.left);
        }
    }
}
