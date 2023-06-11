package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 18:57
 * @Version 1.0
 */
public class _08_463_island_perimeter {

    int[][] grid;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int rows;
    int cols;

    boolean[][] isVisited;
    int res;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        isVisited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }
        return res;
    }

    public void dfs(int i, int j) {
        if (!inArea(i, j) || grid[i][j] == 0 || isVisited[i][j]) {
            return;
        }

        isVisited[i][j] = true;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (!inArea(nexti, nextj) || grid[nexti][nextj] == 0) {
                res += 1;
            }
            dfs(nexti, nextj);
        }
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public int dfs1(int i, int j) {
        if (!inArea(i, j) || grid[i][j] == 0) return 1;
        if (isVisited[i][j]) return 0;
        isVisited[i][j] = true;
        int res = 0;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            res += dfs1(nexti, nextj);
        }
        return res;
    }

    private int bfs(int i, int j) {
        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        isVisited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int nexti = cur[0] + dir[0];
                int nextj = cur[1] + dir[1];
                if (!inArea(nexti, nextj) || grid[nexti][nextj] == 0) {
                    res += 1;
                } else if (!isVisited[nexti][nextj]) {
                    queue.offer(new int[]{nexti, nextj});
                    isVisited[nexti][nextj] = true;
                }
            }
        }
        return res;
    }
}
