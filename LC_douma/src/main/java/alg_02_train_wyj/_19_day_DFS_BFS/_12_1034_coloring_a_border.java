package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-06-01 13:54
 * @Version 1.0
 */
public class _12_1034_coloring_a_border {

    private int rows;
    private int cols;
    private boolean[][] visited;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int oldColor;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        oldColor = grid[row][col];
        if (oldColor == color) return grid;
        dfs(grid, row, col, color);
        return grid;
    }

    public void dfs(int[][] grid, int i, int j, int color) {
        if (!inArea(i, j) || visited[i][j] || grid[i][j] != oldColor) {
            return;
        }
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (!inArea(nexti, nextj) || (grid[nexti][nextj] != oldColor && !visited[nexti][nextj])) {
                grid[i][j] = color;
            }

            dfs(grid, nexti, nextj, color);
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // [[1,1,1],[1,1,1],[1,1,1]]
    //1
    //1
    //2

    // [[2,2,2],[2,2,2],[2,2,2]]

    // [[2,2,2],[2,1,2],[2,2,2]]
}
