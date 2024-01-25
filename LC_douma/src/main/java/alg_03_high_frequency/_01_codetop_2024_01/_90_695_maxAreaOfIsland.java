package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _90_695_maxAreaOfIsland {
    private int[][] grid;
    private int row;
    private int col;
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(i, j);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }

    public int dfs(int i, int j) {
        // 注音：是否越界需要在最开始位置进行判断，避免后续操作的索引越界
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
        return i >= 0 && i < row && j >= 0 && j < col;
    }
}
