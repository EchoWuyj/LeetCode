package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 21:28
 * @Version 1.0
 */
public class _14_994_rotting_oranges2 {

    private int rows;
    private int cols;

    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        rows = grid.length;
        cols = grid[0].length;

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    int index = i * cols + j;
                    queue.offer(index);
                    map.put(index, 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int[] dir : dirs) {
                int nexti = cur / cols + dir[0];
                int nextj = cur % cols + dir[1];
                if (inArea(nexti, nextj) && grid[nexti][nextj] == 1) {
                    grid[nexti][nextj] = 2;
                    int newIndex = nexti * cols + nextj;
                    queue.offer(newIndex);
                    int level = map.get(cur) + 1;
                    map.put(newIndex, level);
                    res = level;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return res;
    }

    public boolean inArea(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
