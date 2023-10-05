package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-02 12:35
 * @Version 1.0
 */
public class _14_994_rotting_oranges1 {

    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int[] arr = new int[]{i, j};
                    queue.offer(arr);
                    map.put(Arrays.toString(arr), 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int nexti = cur[0] + dir[0];
                int nextj = cur[1] + dir[1];
                if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n
                        && grid[nexti][nextj] == 1) {
                    grid[nexti][nextj] = 2;
                    int[] arr = new int[]{nexti, nextj};
                    queue.offer(arr);
                    int level = map.get(Arrays.toString(cur)) + 1;
                    map.put(Arrays.toString(arr), level);
                    res = Math.max(res, level);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return res;
    }
}
