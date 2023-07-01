package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:03
 * @Version 1.0
 */
public class _08_463_island_perimeter1 {

     /*
        463. 岛屿的周长
        给定一个 row x col 的二维网格地图 grid
        其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。

        网格中的格子 水平和垂直 方向相连(对角线方向不相连)
        整个网格被水完全包围，但其中恰好有一个岛屿 (或者说，一个或多个表示陆地的格子相连组成的岛屿)
        岛屿中没有 "湖"("湖" 指水域在岛屿内部 且 不和岛屿周围的水相连)
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

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int res = 0;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        // 没有给初始位置 (row,col)，故只能遍历整个二维数组
        // 找到 grid[row][col] == 1，作为初始位置
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // int 类型，二维数组，判断是否为 1，而不是字符 '1'
                if (grid[row][col] == 1) {
                    dfs1(row, col);
                }
            }
        }
        // 返回全局 res
        return res;
    }

    // KeyPoint 方法一 dfs => 前序遍历
    private void dfs1(int row, int col) {
        // 递归边界
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
        // 直接 return，不用 if else
        // 若使用 if else，则需要保证，每个分支都是得有 return 的
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
