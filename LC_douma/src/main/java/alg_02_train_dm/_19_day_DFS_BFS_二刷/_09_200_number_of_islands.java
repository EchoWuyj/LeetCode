package alg_02_train_dm._19_day_DFS_BFS_二刷;

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

            提示：
            m == grid.length
            n == grid[i].length
            1 <= m, n <= 300
            grid[i][j] 的值为 '0' 或 '1'


     */

    private char[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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
                // 1.若没有加 visited[row][col]，则每次遇到 '1'，res++，即认为是一个岛屿，
                //   但是其实整体才是一个岛屿，进行了重复计算
                // 2.若已经访问的顶点 '1'，for 循环遍历时，直接跳过，因为 '1' 已经属于某个岛屿了
                if (grid[row][col] == '1' && !visited[row][col]) {
                    dfs(row, col);
                    // 每次标记一个岛屿，将 res 累加 1
                    // => 进行几次 dfs，则有多少个岛屿
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

    // KeyPoint 注意事项
    // 若 dfs 返回值 boolean => 一般用于找：方案，路径
    // dfs 逻辑：递归直到递归边界，若最终返回 true，则是找到了，则层层向上返回 true
    // 比如：04_79_word_search 判断在格子中是否存在单词 word
    // 本题不适用
    // => 本题没有 方案 或者 路径 边界条件，最终无法返回 true
    //    而范围边界只是用于排除不合法情况，只能返回 false
    public boolean dfs(char[][] grid, int i, int j) {
        if (!inArea(i, j) || visited[i][j] || grid[i][j] == '0') {
            return false;
        }
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (dfs(grid, nexti, nextj)) return true;
        }
        return false;
    }
}
 