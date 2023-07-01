package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:04
 * @Version 1.0
 */
public class _12_1034_coloring_a_border1 {
      /*
         1034. 边框着色

         给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。
         另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。

         如果两个方块在任意 4 个方向上相邻，则称它们 相邻 。
         如果两个方块具有相同的颜色且相邻，它们则属于同一个 连通分量 。

         连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
         在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
         在网格的边界上（第一行/列或最后一行/列）
         请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色。
         并返回最终的网格 grid 。

         => 四周染色，不包括中间

         示例 1：
         输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
         输出：[[3,3],[3,2]]

            原始
             1 1   初始位置 (0,0)，染色 3
             1 2

            着色
            3 3
            3 2

         示例 2：
         输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
         输出：[[1,3,3],[2,3,3]]

            原始
             1 2 2   初始位置 (0,1)，染色 3
             2 3 2

            着色
            1 3 3
            2 3 3

         提示：
         m == grid.length
         n == grid[i].length
         1 <= m, n <= 50
         1 <= grid[i][j], color <= 1000
         0 <= row < m
         0 <= col < n

     */

    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 记录最开始颜色
    int oldColor;

    // 本质：07_733_flood_fill 图像渲染
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        oldColor = grid[row][col];
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        this.oldColor = grid[row][col];
        if (oldColor == color) return grid;

        // 给定位置进行 dfs
        dfs(row, col, color);
        return grid;
    }

    // 原始版本 dfs => 推荐使用，比较好理解
    public void dfs(int i, int j, int color) {
        // 注意：本题区域内部顶点没有染色，故递归边界，必须加上 visited[i][j]，否则无线递归，导致 StackOverflowError
        if (!inArea(i, j) || grid[i][j] != oldColor || visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            // 判断当前顶点是否为边界，依靠上下左右顶点特点来判断
            // 1.上下左右顶点，有一个不在边界内，则说明该顶点 (i,j) 为边界
            // 2.上下左右顶点 != oldColor 并且没有被访问过，也说明该顶点为边界
            // 优化：递归边界条件 和 该 if 条件差不多考虑合并 => 详见后面
            if (!inArea(nexti, nextj) || (grid[nexti][nextj] != oldColor && !visited[nexti][nextj])) {
                // 根据周围顶点情况，给当前顶点着色
                grid[i][j] = color;
            }
            // dfs 上下左右顶点
            dfs(nexti, nextj, color);
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
