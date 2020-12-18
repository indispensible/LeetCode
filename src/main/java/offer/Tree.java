package offer;

import java.util.*;

/**
 * @author lvgang
 * @date 2020/12/10 16:48
 */
public class Tree {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B != null && A != null) {
            if (B.val == A.val) {
                if (judgeSubStructure(A, B)) {
                    return true;
                }
            }
            if (isSubStructure(A.left, B)) {
                return true;
            }
            if (isSubStructure(A.right, B)) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A != null && A.val == B.val) {
            if (judgeSubStructure(A.left, B.left) && judgeSubStructure(A.right, B.right)) {
                return true;
            }
        }
        return false;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recurIsSymmetric(root.left, root.right);
    }

    public boolean recurIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            if (recurIsSymmetric(left.left, right.right) && recurIsSymmetric(left.right, right.left)) {
                return true;
            }
        }
        return false;
    }

    public int[] levelOrder(TreeNode root) {
        int[] array = new int[1000];
        int length = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root != null) {
            queue.add(root);
            TreeNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                array[length++] = node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        int[] orderArray = Arrays.copyOf(array, length);
        return orderArray;
    }

    public List<List<Integer>> newLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int depth = 1;
        if (root != null) {
            queue.add(root);
            newLevelOrder(queue, 1, lists);
        }
        return lists;
    }

    public void newLevelOrder(Queue<TreeNode> queue, int depth, List<List<Integer>> lists) {
        if (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (depth > lists.size()) {
                List<Integer> list = new LinkedList<Integer>();
                lists.add(list);
            }
            lists.get(depth - 1).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                newLevelOrder(queue, depth + 1, lists);
            }
            if (node.right != null) {
                queue.add(node.right);
                newLevelOrder(queue, depth + 1, lists);
            }
        }
    }

    List<List<Integer>> lists = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root != null) {
            levelOrder3(root, 1);
        }
        return lists;
    }

    public void levelOrder3(TreeNode node, int depth) {
        List<Integer> list;
        if (depth > lists.size()) {
            list = new LinkedList<Integer>();
            lists.add(list);
        }
        list = lists.get(depth - 1);
        if ((depth & 1) == 1) {
            list.add(node.val);
        } else {
            list.add(0, node.val);
        }
        if (node.left != null) {
            levelOrder3(node.left, depth + 1);
        }
        if (node.right != null) {
            levelOrder3(node.right, depth + 1);
        }
    }

    List<List<Integer>> pathLists = new ArrayList<List<Integer>>();
    int Sum;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        Sum = sum;
        if (root != null) {
            List<Integer> list = new ArrayList<Integer>();
            pathSum(root, 0, list);
        }
        return pathLists;
    }

    public void pathSum(TreeNode node, int sum, List<Integer> list) {
        list.add(node.val);
        if (node.val + sum == this.Sum) {
            if (node.left == null && node.right == null) {
                pathLists.add(new ArrayList<Integer>(list));
            }
        }
        if (node.left != null) {
            pathSum(node.left, node.val + sum, list);
        }
        if (node.right != null) {
            pathSum(node.right, node.val + sum, list);
        }
        list.remove(list.size() - 1);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        StringBuilder ser = new StringBuilder();
        TreeNode tmp = null;
        if (root != null) {
            queue.add(root);
            ser.append("[");
            while (!queue.isEmpty()) {
                tmp = queue.poll();
                if (tmp != null) {
                    ser.append(tmp.val).append(",");
                    queue.add(tmp.left);
                    queue.add(tmp.right);
                } else {
                    ser.append("null").append(",");
                }
            }
            ser.deleteCharAt(ser.length() - 1).append("]");
        }
        return ser.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data) || "[]".equals(data)) {
            return null;
        }
        data = data.substring(1, data.length() - 1);
        String[] strings = data.split(",");
        TreeNode parent = null, cur, head = null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals("null")) {
                cur = new TreeNode(Integer.parseInt(strings[i]));
                queue.add(cur);
            } else {
                cur = null;
            }
            if (i == 0) {
                head = cur;
            }
            if ((i & 1) == 1) {
                parent = queue.poll();
            }
            if (parent != null && (i & 1) == 1) {
                parent.left = cur;
            }
            if (parent != null && (i & 1) == 0) {
                parent.right = cur;
            }
        }
        return head;
    }
}
