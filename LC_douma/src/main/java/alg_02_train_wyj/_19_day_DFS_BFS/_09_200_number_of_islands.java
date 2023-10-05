package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 21:19
 * @Version 1.0
 */
public class _09_200_number_of_islands {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private char[][] grid;
    private boolean[][] visited;
    private int rows;
    private int cols;
    private int res;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(int i, int j) {
        if (!inArea(i, j) || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
