package alg_02_train_dm._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:03
 * @Version 1.0
 */
public class _08_463_island_perimeter {

     /*
        463. 岛屿的周长
        给定一个 row x col 的二维网格地图 grid
        其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。

        网格中的格子 水平和垂直 方向相连(对角线方向不相连)
        整个网格被水完全包围，但其中恰好有一个岛屿 (或者说，一个或多个表示陆地的格子相连组成的岛屿)
        岛屿中没有 "湖"("湖" 指水域在岛屿内部且不和岛屿周围的水相连)
        格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。

        计算这个岛屿的周长。

        示例 1：
        输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
        输出：16
        解释：它的周长是上面图片中的 16 个黄色的边

        提示：
        row == grid.length
        col == grid[i].length
        1 <= row, col <= 100
        grid[i][j] 为 0 或 1
     */

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] grid;
    private int rows;
    private int cols;

    private boolean[][] visited;
    private int res = 0;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        // 遍历整个二维数组
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // int 类型，二维数组，判断是否为 1，而不是字符 '1'
                if (grid[row][col] == 1) {

                    // KeyPoint 3种实现方式
                    // 1.前序遍历
//                    dfs1(row, col);

                    // 2.后续遍历
//                    return dfs(row, col);

                    // 3.广度优先遍历
                    return bfs(row, col);
                }
            }
        }
        return 0;
    }

    // KeyPoint 方法一 dfs => 前序遍历
    private void dfs1(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return;
        }
        // 先将其设置为被访问
        visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            // 前序遍历：在 dfs 执行前，先计算节点周长
            // 越界 或者 [nextRow][nextCol] == 0 即周围是水 => 周长需要累加 1
            if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                res += 1;
            }
            dfs1(nextRow, nextCol);
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // KeyPoint 方法二 dfs => 后序遍历
    // 后序遍历：先不计算该节点 (row,col) 周长，而是计算该节点 (row,col) 上下左右节点的周长之和
    private int dfs(int row, int col) {
        // 上下左右节点中 => 越界 + 水域，则周长 1
        if (!inArea(row, col) || grid[row][col] == 0) return 1;
        // 上下左右节点中 => 该节点已经被访问，则周长 0
        if (visited[row][col]) return 0;

        // 先将其设置为被访问
        visited[row][col] = true;

        // 后序遍历：先不计算该节点 (row,col) 周长，而是计算该节点 (row,col) 上下左右节点的周长之和
        int res = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 递归计算上下左右节点周长，并将其累加 res，即为当前节点的周长
            res += dfs(nextRow, nextCol);
        }
        return res;
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
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return res;
    }
}
