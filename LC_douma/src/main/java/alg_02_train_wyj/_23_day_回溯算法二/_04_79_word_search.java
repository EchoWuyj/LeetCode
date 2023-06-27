package alg_02_train_wyj._23_day_回溯算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 17:09
 * @Version 1.0
 */
public class _04_79_word_search {

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static boolean[][] visited;

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, m, n, i, j, 0)) return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word,
                              int m, int n, int row, int col, int index) {
        if (index == word.length() - 1) return true;
        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m
                    && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]
                    && board[nextRow][nextCol] == word.charAt(index + 1)) {
                if (dfs(board, word, m, n, nextRow, nextCol, index + 1)) return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

    // dfs 另外一种方式
    public static boolean dfs1(char[][] board, String word,
                               int m, int n, int row, int col, int index) {

        if (index == word.length()) return true;
        if (row < 0 || row >= m || col < 0 || col >= n ||
                visited[row][col] || board[row][col] != word.charAt(index))
            return false;

        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (dfs1(board, word, m, n, nextRow, nextCol, index + 1)) return true;
        }

        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'a'}};
        System.out.println(exist(board, "a"));
    }
}
