package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:04
 * @Version 1.0
 */
public class _10_695_max_area_of_island1 {
    
   /*
        695. 岛屿的最大面积
        给定一个包含了一些 0 和 1 的非空二维数组 grid 。

        一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
        这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
        你可以假设 grid 的四个边缘都被 0(代表水)包围着。

        找到给定的二维数组中最大的岛屿面积(如果没有岛屿，则返回面积为 0)

        提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] 为 0 或 1

     */

    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // DFS
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        // 每次 dfs 遍历到一个新顶点，定义当前顶点的 res
        int res = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 顶点是土地，且不能被访问过(避免重复计算)
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int area = dfs(row, col);
                    res = Math.max(area, res);
                }
            }
        }
        return res;
    }

    // 后序遍历 => 一般涉及值计算，通常使用后序遍历
    // 先将上下左右顶点值计算出来，再加上当前顶点的值
    private int dfs(int row, int col) {
        // 已经统计过的顶点不要重复计算
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            // 不符合条件节点，面积都是 0
            return 0;
        }

        visited[row][col] = true;

        int res = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 累加上下左右顶点面积
            res += dfs(nextRow, nextCol);
        }
        // 返回 res 加 1，表示该节点自身面积
        // 若四周顶点都符合条件，也有自身为 1
        return 1 + res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
