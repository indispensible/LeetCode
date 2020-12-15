package offer;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author lvgang
 * @date 2020/12/11 12:40
 */
public class Order {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length, n = matrix[0].length, row = 0, column = -1, direction = 1, i = 0;
        int[] array = new int[m * n];
        int[][] walked = new int[m][n];
        while (i != m * n){
            if (direction == 1) {
                if (column + 1 < n && walked[row][column + 1] == 0) {
                    column++;
                    walked[row][column] = 1;
                    array[i++] = matrix[row][column];
                } else {
                    direction = 2;
                }
            }
            if (direction == 2) {
                if (row + 1 < m && walked[row + 1][column] == 0) {
                    row++;
                    walked[row][column] = 1;
                    array[i++] = matrix[row][column];
                } else {
                    direction = 3;
                }
            }
            if (direction == 3) {
                if (column - 1 > -1 && walked[row][column - 1] == 0) {
                    column--;
                    walked[row][column] = 1;
                    array[i++] = matrix[row][column];
                } else {
                    direction = 4;
                }
            }
            if (direction == 4) {
                if (row - 1 > -1 && walked[row - 1][column] == 0) {
                    row--;
                    walked[row][column] = 1;
                    array[i++] = matrix[row][column];
                } else {
                    direction = 1;
                }
            }
        }
        return array;
    }

    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int m = left;
        while (m < right && postorder[m] < postorder[right]) {
            m++;
        }
        for (int i = m; i < right; i++) {
            if (postorder[i] < postorder[right]) {
                return false;
            }
        }
        return verifyPostorder(postorder, left, m - 1) && verifyPostorder(postorder, m, right - 1);
    }

    public static void main(String[] args) {
        Order order = new Order();
        int[] postorder = {1,3,2,6,5};
        System.out.println(order.verifyPostorder(postorder));
    }
}
