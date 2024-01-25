package alg_02_train_wyj._19_day_DFS_BFS;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 21:19
 * @Version 1.0
 */
public class _09_200_number_of_islands {

    int row;
    int col;
    boolean[][] visited;
    char[][] grid;
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int res = 0;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j) {
        if (!inArea(i, j) || visited[i][j] || grid[i][j] == '0') return;
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < col;
    }
}
