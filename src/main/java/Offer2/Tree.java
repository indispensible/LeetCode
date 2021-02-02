package Offer2;

import offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lvgang
 * @date 2021/1/28 10:10
 */
public class Tree {
    Queue<TreeNode> queue = new LinkedList<>();

    public void flatten(TreeNode root) {
        recur(root);
        TreeNode node = null;
        while (queue.size() > 0) {
            node = queue.poll();
            node.left = null;
            if (queue.size() > 0) {
                node.right = queue.peek();
            } else {
                node = null;
            }
        }
    }

    public void recur(TreeNode node) {
        if (node != null) {
            queue.add(node);
            recur(node.left);
            recur(node.right);
        }
    }
}
