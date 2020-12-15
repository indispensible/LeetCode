package offer;

/**
 * @author lvgang
 * @date 2020/12/4 15:47
 */
public class MovingCount {
    public int movingCount(int m, int n, int k) {
        int count = 0;
        int[][] cells = new int[m][n];
        count = movingCount(cells, count, k, 0, 0);
        return count;
    }

    public int movingCount(int[][] cells, int count, int k, int row, int column) {
        if (row > -1 && row < cells.length && column > -1 && column < cells[0].length && cells[row][column] == 0 && judgeGTK(row, column, k)) {
            count += 1;
            cells[row][column] = 1;
            count = movingCount(cells, count, k, row - 1, column);
            count = movingCount(cells, count, k, row + 1, column);
            count = movingCount(cells, count, k, row, column - 1);
            count = movingCount(cells, count, k, row, column + 1);
        }
        return count;
    }

    public boolean judgeGTK(int row, int column, int k) {
        int sum = 0;
        while (row != 0) {
            sum += row % 10;
            row /= 10;
        }
        while (column != 0) {
            sum += column % 10;
            column /= 10;
        }
        if (sum > k) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MovingCount movingCount = new MovingCount();
        System.out.println(movingCount.movingCount(2, 3, 1));
        System.out.println(movingCount.movingCount(3, 1, 0));
    }
}
