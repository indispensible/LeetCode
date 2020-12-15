package offer;

/**
 * @author lvgang
 * @date 2020/12/4 10:57
 */
public class Exist {
    public boolean exist(char[][] board, String word) {
        boolean judge = false;
        int[][] walked_path = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                judge = exist(board, word, walked_path, i, j, 0);
                if (judge) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int[][] walked_path, int row, int column, int word_num) {
        boolean judge = false;
        if (-1 < row && row < board.length && -1 < column && column < board[0].length && walked_path[row][column] == 0 && board[row][column] == word.charAt(word_num)) {
            judge = true;
            walked_path[row][column] = 1;
            if (word_num != word.length() - 1) {
                judge = exist(board, word, walked_path, row - 1, column, word_num + 1);
                if (judge) {
                    return true;
                }
                judge = exist(board, word, walked_path, row + 1, column, word_num + 1);
                if (judge) {
                    return true;
                }
                judge = exist(board, word, walked_path, row, column - 1, word_num + 1);
                if (judge) {
                    return true;
                }
                judge = exist(board, word, walked_path, row, column + 1, word_num + 1);
                if (judge) {
                    return true;
                }
            }
            walked_path[row][column] = 0;
        }
        return judge;
    }

    public static void main(String[] args) {
        Exist exist = new Exist();
        char[][] board = {{'c', 'a', 'a'}, {'a', 'a', 'a'}, {'b', 'c', 'd'}};
        System.out.println(exist.exist(board, "aab"));
    }
}
