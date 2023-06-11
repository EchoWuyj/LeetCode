package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-06-01 13:54
 * @Version 1.0
 */
public class _12_1034_coloring_a_border {

    int[][] grid;
    int rows;
    int cols;
    boolean[][] visited;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int curColor;
    int color;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        this.color = color;
        curColor = grid[row][col];
        rows = this.grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        if (curColor == color) return grid;
//        dfs(row, col);
        dfs_op(row, col);
        return grid;
    }

    public void dfs(int i, int j) {
        if (!inArea(i, j) || grid[i][j] != curColor || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (!inArea(nexti, nextj) || (grid[nexti][nextj] != curColor && !visited[nexti][nextj])) {
                grid[i][j] = color;
            }
            dfs(nexti, nextj);
        }
    }

    public void dfs_op(int i, int j) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (!inArea(nexti, nextj) || (grid[nexti][nextj] != curColor && !visited[nexti][nextj])) {
                grid[i][j] = color;
            } else if (!visited[nexti][nextj]) {
                dfs_op(nexti, nextj);
            }
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
