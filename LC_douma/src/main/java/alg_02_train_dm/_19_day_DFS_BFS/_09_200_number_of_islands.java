package alg_02_train_dm._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:04
 * @Version 1.0
 */
public class _09_200_number_of_islands {
       /*
            200. 岛屿数量
            给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
            岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
            此外，你可以假设该网格的四条边均被水包围。

            示例 1：

            输入：grid = [
              ["1","1","1","1","0"],
              ["1","1","0","1","0"],
              ["1","1","0","0","0"],
              ["0","0","0","0","0"]
            ]
            输出： 1

            示例 2：

            输入：grid = [
              ["1","1","0","0","0"],
              ["1","1","0","0","0"],
              ["0","0","1","0","0"],
              ["0","0","0","1","1"]
            ]
            输出： 3
     */

    private char[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // DFS
    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        // 记得 visited 需要初始化，否则空指针异常
        this.visited = new boolean[rows][cols];

        int res = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 使用 visited 来记录顶点是否已经访问
                // 若已经访问的顶点 '1'，for 循环遍历时，直接跳过，因为 '1' 已经属于某个岛屿了
                if (grid[row][col] == '1' && !visited[row][col]) {
                    dfs(row, col);
                    // 每次标记一个岛屿，将 res 累加 1 => 进行几次 dfs，则有多少个岛屿
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(int row, int col) {
        // 遇到这 3 种情况进行回溯
        // grid[row][col] == '0'，使用字符 '0'，不是数字 0，特别小心
        if (!inArea(row, col) || grid[row][col] == '0' || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol);
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
 