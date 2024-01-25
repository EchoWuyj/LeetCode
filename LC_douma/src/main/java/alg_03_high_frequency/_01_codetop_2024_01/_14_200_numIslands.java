package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 23:36
 * @Version 1.0
 */
public class _14_200_numIslands {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private char[][] grid;
    // 通过数据，标记 i 和 j
    private boolean[][] visited;
    private int rows;
    private int cols;
    private int res;

    public int numIslands(char[][] grid) {
        // 初始化
        this.grid = grid;
        // 不能再去定义 int rows 和 int cols，得使用全局的 rows 和 cols
        // 否则本地的 rows 和 cols 覆盖全局的 rows 和 cols，导致后续的代码出现 bug
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
        // 设置访问
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(nexti, nextj);
        }
    }

    // 返回值为 boolean
    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
