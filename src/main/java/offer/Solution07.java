package offer;

import java.util.Arrays;

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
//}


/**
 * @author lvgang
 * @date 2020/12/1 9:51
 */
public class Solution07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = null;
        if (preorder.length > 0) {
            root = new TreeNode(preorder[0]);
            int root_index;
            for (root_index = 0; root_index < preorder.length; root_index++) {
                if (inorder[root_index] == root.val) {
                    break;
                }
            }
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, root_index + 1), Arrays.copyOfRange(inorder, 0, root_index));
            root.right = buildTree(Arrays.copyOfRange(preorder, root_index + 1, preorder.length), Arrays.copyOfRange(inorder, root_index + 1, inorder.length));
        }
        return root;
    }

    public static void main(String[] args) {

    }

}
