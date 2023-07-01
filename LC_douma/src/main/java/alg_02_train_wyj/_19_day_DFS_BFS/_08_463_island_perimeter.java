package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 18:57
 * @Version 1.0
 */
public class _08_463_island_perimeter {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int res = 0;

    public int islandPerimeter(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return dfs1(grid, i, j);
                }
            }
        }
        return 0;
    }

    public void dfs(int[][] grid, int i, int j) {

        if (!inArea(i, j) || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (!inArea(nexti, nextj) || grid[nexti][nextj] == 0) res++;
            dfs(grid, nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public int dfs1(int[][] grid, int i, int j) {

        if (!inArea(i, j) || grid[i][j] == 0) return 1;
        if (visited[i][j]) return 0;
        visited[i][j] = true;

        int res = 0;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            res += dfs1(grid, nexti, nextj);
        }
        return res;
    }

    public int bfs(int[][] grid, int i, int j) {

        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) res++;
                else if (!visited[nextRow][nextCol]) {
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return res;
    }
}
