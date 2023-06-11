package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-02 12:35
 * @Version 1.0
 */
public class _14_994_rotting_oranges {

    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;

        Map<String, Integer> map = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    int[] tmp = new int[]{i, j};
                    queue.offer(tmp);
                    map.put(Arrays.toString(tmp), 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int nexti = cur[0] + dir[0];
                int nextj = cur[1] + dir[1];
                if (nexti >= 0 && nexti < rows && nextj >= 0 && nextj < cols
                        && grid[nexti][nextj] == 1) {
                    grid[nexti][nextj] = 2;
                    int[] newArr = new int[]{nexti, nextj};
                    queue.offer(newArr);
                    int level = map.get(Arrays.toString(cur)) + 1;
                    map.put(Arrays.toString(newArr), level);
                    res = level;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return res;
    }

    public int orangesRotting1(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;

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
            int curIndex = queue.poll();
            int i = curIndex / cols;
            int j = curIndex % cols;
            for (int[] dir : dirs) {
                int nexti = i + dir[0];
                int nextj = j + dir[1];
                if (nexti >= 0 && nexti < 0 && nextj >= 0 && nextj < cols
                        && grid[nexti][nextj] == 1) {
                    grid[nexti][nextj] = 2;
                    int newIndex = nexti * cols + nextj;
                    queue.offer(newIndex);

                    int level = map.get(curIndex) + 1;
                    map.put(newIndex, level);
                    res = level;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return res;
    }
}
