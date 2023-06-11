package alg_02_train_dm._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:04
 * @Version 1.0
 */
public class _12_1034_coloring_a_border {
      /*
         1034. 边框着色
         给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。
         只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。

         连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，
         或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。

         给出位于 (r0, c0) 的网格块和颜色 color，
         使用指定颜色 color 为所给网格块的连通分量的边界进行着色，并返回最终的网格 grid 。

         示例 1：
         输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
         输出：[[3,3],[3,2]]

     */

    int[][] grid;
    int rows;
    int cols;
    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 记录最开始颜色
    int currColor;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        currColor = grid[row][col];
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        this.currColor = grid[row][col];
        if (currColor == color) return grid;

        // 给定位置进行 dfs
//        dfs(row, col, color);
        dfs_op(row, col, color);

        return grid;
    }

    // 原始版本 dfs => 推荐使用，比较好理解
    public void dfs(int i, int j, int color) {
        if (!inArea(i, j) || grid[i][j] != currColor || visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        // 判断当前顶点是否为边界，依靠上下左右顶点特点来判断
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            // 1.上下左右顶点有一个不在边界内，则说明该顶点为边界
            // 2.上下左右顶点 != curColor 并且没有被访问过，也说明该顶点为边界
            if (!inArea(nexti, nextj) || (grid[nexti][nextj] != currColor && !visited[nexti][nextj])) {
                // 根据周围顶点情况，给当前顶点着色
                grid[i][j] = color;
            }
            // dfs 上下左右顶点
            dfs(nexti, nextj, color);
        }
    }

    // 优化 dfs
    private void dfs_op(int i, int col, int color) {
        visited[i][col] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = col + dir[1];
            // 判断当前顶点是否为边界，依靠上下左右顶点特点来判断
            // 1.上下左右顶点有一个不在边界内，则说明该顶点为边界
            // 2.上下左右顶点 != curColor 并且没有被访问过，也说明该顶点为边界
            if (!inArea(nexti, nextj)
                    || (grid[nexti][nextj] != currColor && !visited[nexti][nextj])) {
                grid[i][col] = color;

                // KeyPoint 复杂逻辑取反
                // (a || (b&&c)) 相反条件 => (¬a && (¬b || ¬c))
                // 将 "||" 运算符替换为 "&&" 运算符，"&&" 替换为 "||"，字母取反
                // => 经过 if 分支，到达 else if 已经是逻辑取反

            } else if (!visited[nexti][nextj]) {

                // KeyPoint dfs 另外一种写法
                // 只有在符合条件的下才会执行 dfs，不符合条件都不会进行 dfs，即也就是遇到递归边界，回溯
                // 故可以将原始的 dfs 边界条件 if 去掉
                // 总结：执行 dfs 之前先进行 if 判断，则可以省略 dfs中递归边界 if 判断，否则就得加上

                // 递归调用保证 dfs_op 方法名一致性，特别需要注意
                dfs_op(nexti, nextj, color);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
