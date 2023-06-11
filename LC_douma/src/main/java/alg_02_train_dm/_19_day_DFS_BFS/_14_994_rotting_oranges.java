package alg_02_train_dm._19_day_DFS_BFS;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:05
 * @Version 1.0
 */
public class _14_994_rotting_oranges {
     /*
        994. 腐烂的橘子
        在给定的网格中，每个单元格可以有以下三个值之一：
        值 0 代表空单元格；
        值 1 代表新鲜橘子；
        值 2 代表腐烂的橘子。

        每分钟，任何与腐烂的橘子(在 4 个正方向上)相邻的新鲜橘子都会腐烂。

        返回直到单元格中没有新鲜橘子为止，所必须经过的最小分钟数。如果不可能，返回 -1。

        输入：[[2,1,1],
              [1,1,0],
              [0,1,1]]
        输出：4

        输入：[[2,1,1],
              [0,1,1],
              [1,0,1]]
        输出：-1
     */

    // KeyPoint 多源广度优先遍历
    // 1.广度优先遍历 => 每次遍历当前顶点的下一层顶点 => 本题使用 => 相邻橘子同时腐烂，不是一个一个腐烂
    // 2.深度优先遍历 => 每次遍历当前顶点的下一个顶点
    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        // 优化：现在使用二维数组的行索引 + 列索引转换成一维数组中的索引作为 Map 的 key
        Map<Integer, Integer> levelsMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        // 遍历二维数组，找到腐烂橘子，将其加入队列中
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    int index = row * cols + col;
                    queue.offer(index);
                    // 记录每个橘子的腐烂时刻
                    // levelsMap 的 key 不要直接使用数组 int[] tmp，通过 Arrays.toString(tmp) 转成字符串形式
                    levelsMap.put(index, 0);
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int currIndex = queue.poll();
            int row = currIndex / cols;
            int col = currIndex % cols;
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && grid[nextRow][nextCol] == 1) {
                    // 访问之后橘子，已经将其修改成 2，不再是 1 了，相当于做了 visited[i][j]
                    // grid[nextRow][nextCol] 包含了 visited 数组的效果，故可以省略
                    grid[nextRow][nextCol] = 2;
                    int index = nextRow * cols + nextCol;
                    queue.offer(index);

                    // 更新所属层信息
                    int level = levelsMap.get(currIndex) + 1;
                    levelsMap.put(index, level);

                    // 记录最后一层橘子层数
                    res = level;
                }
            }
        }

        // 返回前判断下是否仍然有新鲜的橘子
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) return -1;
            }
        }

        return res;
    }

    // KeyPoint 另外一种形式：数组转换字符串 => 效率低下，不推荐
    // 定义二维数组形式 int[] tmp，通过 Arrays.toString(cur) 转字符串，作为 Map 的 key形式
    public int orangesRotting1(int[][] grid) {
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
}
