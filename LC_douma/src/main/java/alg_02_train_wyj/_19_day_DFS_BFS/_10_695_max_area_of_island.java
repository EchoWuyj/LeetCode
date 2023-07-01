package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 21:45
 * @Version 1.0
 */
public class _10_695_max_area_of_island {

    private int[][] grid;
    private boolean[][] visited;
    private int rows;
    private int cols;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    public int dfs(int i, int j) {
        if (!inArea(i, j) || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int res = 0;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            res += dfs(nexti, nextj);
        }
        return res + 1;
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
