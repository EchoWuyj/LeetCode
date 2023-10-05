package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:05
 * @Version 1.0
 */
public class _14_994_rotting_oranges1_推荐 {
     /*
        994. 腐烂的橘子
        在给定的网格中，每个单元格可以有以下三个值之一：
        值 0 代表空单元格；
        值 1 代表新鲜橘子；
        值 2 代表腐烂的橘子。

        每分钟，任何与腐烂的橘子(在 4 个正方向上)相邻的新鲜橘子都会腐烂。
        返回直到单元格中没有新鲜橘子为止，所必须经过的最小分钟数。
        如果不可能，返回 -1。

        输入：[[2,1,1],
              [1,1,0],
              [0,1,1]]
        输出：4

        输入：[[2,1,1],
              [0,1,1],
              [1,0,1]]
        输出：-1
     */

    // KeyPoint BFS => 一层一层访问
    // 多源 广度 优先遍历 (多个源点同时进行广度优先遍历)
    // 1.广度优先遍历 => 每次遍历当前顶点的下一层顶点 => 本题使用
    //               => 相邻橘子同时腐烂，不是一个一个腐烂
    // 2.深度优先遍历 => 每次遍历当前顶点的下一个顶点
    public int orangesRotting1(int[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;

        Map<String, Integer> map = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    int[] arr = new int[]{i, j};
                    queue.offer(arr);
                    // 数组 转换 字符串 => 效率低下
                    // 注意：不直接将 数组 作为 Map 的 key，而是将其转成 String
                    // key => 顶点 (row,col) 索引坐标
                    // value => 腐烂时间
                    map.put(Arrays.toString(arr), 0);
                }
            }
        }

        // 定义变量，最好初始化，严谨一点
        int res = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int nexti = cur[0] + dir[0];
                int nextj = cur[1] + dir[1];
                // 在范围内，且是新鲜橘子
                if (nexti >= 0 && nexti < rows && nextj >= 0 && nextj < cols
                        && grid[nexti][nextj] == 1) {
                    // 腐烂新鲜橘子后，其值修改成 2，不再是 1 了
                    // => 相当于标记了 visited，故不需要额外的 visited 数组
                    grid[nexti][nextj] = 2;
                    int[] newArr = new int[]{nexti, nextj};
                    queue.offer(newArr);
                    // KeyPoint 更新腐烂时间
                    // => 在 cur 当前顶点腐烂时间上加 1
                    // 注意：获取是 cur 的 value
                    int level = map.get(Arrays.toString(cur)) + 1;
                    map.put(Arrays.toString(newArr), level);
                    // 记录最后一层橘子层数 => 腐烂时间
                    // res = level;
                    // 最好使用 Math.max 嵌套一层，保证不会被小值覆盖
                    res = Math.max(level, res);
                }
            }
        }

        // 返回前判断下是否仍然有新鲜的橘子
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return res;
    }
}
