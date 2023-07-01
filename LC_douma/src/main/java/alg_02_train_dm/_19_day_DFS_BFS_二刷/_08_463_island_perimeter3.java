package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 12:45
 * @Version 1.0
 */
public class _08_463_island_perimeter3 {

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    // 广度优先遍历
                    return bfs(row, col);
                }
            }
        }
        return 0;
    }

    // KeyPoint 方法三 广度优先遍历
    private int bfs(int row, int col) {
        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                // 在'四连通'或者'八连通'中，使用的都是 nextRow 和 nextCol，不是原来的 row 和 col
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                    res += 1;
                } else if (!visited[nextRow][nextCol]) {
                    queue.offer(new int[]{nextRow, nextCol});
                    // 测试用例 超出时间限制
                    // => 说明 BFS 中存在环路
                    // => 没有将已经访问的顶点设置为已访问，从而导致走回头路
                    visited[nextRow][nextCol] = true;
                }
                // 若 visited[nextRow][nextCol] 已经访问过的
                // => 不用进行操作，即不用再将其加入队列中
            }
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
