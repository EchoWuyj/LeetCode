package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 21:19
 * @Version 1.0
 */
public class _09_200_number_of_islands {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int res = 0;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (!inArea(i, j) || visited[i][j] || grid[i][j] == '0') {
            return;
        }
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(grid, nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
