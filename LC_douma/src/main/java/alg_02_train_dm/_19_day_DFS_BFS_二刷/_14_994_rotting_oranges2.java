package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 20:26
 * @Version 1.0
 */
public class _14_994_rotting_oranges2 {

    // 执行用时：2 ms，在所有 Java 提交中击败了 37.79% 的用户
    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        // 优化：
        // 二维数组 行索引 和 列索引 => 一维数组中索引，作为 Map 的 key
        // 避免 数组 转 String，提高效率
        // Queue 中存的是索引，而不是数组，关键在 索引 转换
        Map<Integer, Integer> levelsMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    // 二维 转 一维
                    int index = row * cols + col;
                    queue.offer(index);
                    levelsMap.put(index, 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int currIndex = queue.poll();
            // 一维 转 二维
            // 1.始终和列 cols 有关
            // 2.行 / 列 %
            int row = currIndex / cols;
            int col = currIndex % cols;
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && grid[nextRow][nextCol] == 1) {
                    grid[nextRow][nextCol] = 2;
                    int index = nextRow * cols + nextCol;
                    queue.offer(index);

                    int level = levelsMap.get(currIndex) + 1;
                    levelsMap.put(index, level);

                    res = level;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) return -1;
            }
        }
        return res;
    }
}
