package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 19:45
 * @Version 1.0
 */
public class _12_1034_coloring_a_border2 {

    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int oldColor;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        oldColor = grid[row][col];
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        oldColor = grid[row][col];

        if (oldColor == color) return grid;
        dfs(row, col, color);
        return grid;
    }

    // dfs 递归条件后置
    private void dfs(int i, int j, int color) {

        // 因为递归判断条件后置
        // => 在进入递归前，已经判断过条件了，直接标记 visited
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];

            // 判断当前顶点是否为边界，依靠上下左右顶点特点来判断
            // 1.上下左右顶点有一个不在边界内，则说明该顶点为边界
            // 2.上下左右顶点 != curColor 并且没有被访问过，也说明该顶点为边界
            if (!inArea(nexti, nextj)
                    || (grid[nexti][nextj] != oldColor && !visited[nexti][nextj])) {
                grid[i][j] = color;

                // KeyPoint 复杂逻辑取反
                // (a || (b && c)) 相反条件 => (¬a && (¬b || ¬c))
                // 将 "||" 运算符替换为 "&&" 运算符，"&&" 替换为 "||"，字母取反

                // 同时，经过 if 分支，到达 else if 已经是逻辑取反
                // !inArea(nexti, nextj) || (grid[nexti][nextj] != oldColor && !visited[nexti][nextj])
                // => inArea(nexti, nextj) && (grid[nexti][nextj] == oldColor || visited[nexti][nextj])
                // => 间接形成了 dfs 递归后置条件
                // 同时，加上 !visited[nexti][nextj]，对条件又进行了限制
            } else if (!visited[nexti][nextj]) {
                // 递归调用保证 dfs 方法名一致性，特别需要注意
                dfs(nexti, nextj, color);
            }

            // KeyPoint dfs 递归条件后置
            // 递归条件后置：只有在符合条件的下才会执行 dfs，不符合条件都不会进行 dfs
            //              即也就是遇到递归边界，故可以将原始的 dfs 边界条件 if 去掉
            // 总结：执行 dfs 之前先进行 if 判断，则可以省略 dfs中递归边界 if 判断，否则就得加上
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
