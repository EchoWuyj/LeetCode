package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:04
 * @Version 1.0
 */
public class _11_130_surrounded_regions {
        /* 
            130. 被围绕的区域
            给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
            找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
        
            输入：board = [["X","X","X","X"],
                          ["X","O","O","X"],
                          ["X","X","O","X"],
                          ["X","O","X","X"]]
        
            输出：[["X","X","X","X"],
                  ["X","X","X","X"],
                  ["X","X","X","X"],
                  ["X","O","X","X"]]
        
            解释：被围绕的区间不会存在于边界上，换句话说，
            任何边界上的 'O' 都不会被填充为 'X'。
            任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
            如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。


            提示：
            m == board.length
            n == board[i].length
            1 <= m, n <= 200
            board[i][j] 为 'X' 或 'O'

     */

    private char[][] board;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];

        // 1.第一次遍历：从四周查找 O 字母，并且将 O 标记已访问
        // 遍历 j 对应'行'
        for (int j = 0; j < cols; j++) {
            // 1. 第一行
            // 注意区别：数字 0 和 字母 O，关键中间是否有斜杆
            if (board[0][j] == 'O' && !visited[0][j]) dfs(0, j);

            // 2. 最后一行
            if (board[rows - 1][j] == 'O' && !visited[rows - 1][j]) dfs(rows - 1, j);
        }

        // 遍历 i 对应'列'
        for (int i = 0; i < rows; i++) {
            // 3. 第一列
            if (board[i][0] == 'O' && !visited[i][0]) dfs(i, 0);

            // 4. 最后一列
            if (board[i][cols - 1] == 'O' && !visited[i][cols - 1]) dfs(i, cols - 1);
        }

        // 2.第二次遍历：将没有被访问过并且等于 O 的字母填充为 X
        // 第二行，第二列，倒数第二行，倒数第二列
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                // 顶点为 'O' 且没有被访问过，需要使用 'X' 替换
                if (board[row][col] == 'O' && !visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }
    }

    private void dfs(int row, int col) {
        if (!inArea(row, col) || board[row][col] == 'X' || visited[row][col])
            // 回溯
            return;

        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol);

            // KeyPoint 不需要还原现场
            // => dfs 中只是递归边界中判断 board[row][col] 是否为 'X'，并没有实际修改成 'X'
//            board[row][col] = 'O';
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
