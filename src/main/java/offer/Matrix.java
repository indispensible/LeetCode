package offer;

/**
 * @author lvgang
 * @date 2020/12/23 9:34
 */
public class Matrix {

    private int maxValue = 0;

    private int[][] dpGrid = new int[0][0];

    public int quickMaxValue(int[][] grid) {
        if (grid.length != 0 && grid[0].length != 0) {
            dpGrid = new int[grid.length][grid[0].length];
            dpGrid[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    if (j + 1 < grid[i].length) {
                        dpGrid[i][j] = Math.max(dpGrid[i][j], grid[i][j] + dpGrid[i][j + 1]);
                    }
                    if (i + 1 < grid.length) {
                        dpGrid[i][j] = Math.max(dpGrid[i][j], grid[i][j] + dpGrid[i + 1][j]);
                    }
                }
            }
        }
        return dpGrid[0][0];
    }

    public void quickMaxValue(int[][] grid, int x, int y) {

    }

    public int maxValue(int[][] grid) {
        if (grid.length != 0 && grid[0].length != 0) {
            maxValue(grid, 0 ,0, 0);
        }
        return maxValue;
    }

    public void maxValue(int[][] grid, int x, int y, int sum) {
        if (x < grid.length && y < grid[0].length) {
            sum += grid[x][y];
            if (x == grid.length - 1 && y == grid[0].length - 1) {
                maxValue = Math.max(maxValue, sum);
                return;
            }
            maxValue(grid, x + 1, y, sum);
            maxValue(grid, x, y + 1, sum);
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        int[][] grid = {{1, 2, 5}, {3, 2, 1}};
        System.out.println(matrix.maxValue(grid));
    }
}
