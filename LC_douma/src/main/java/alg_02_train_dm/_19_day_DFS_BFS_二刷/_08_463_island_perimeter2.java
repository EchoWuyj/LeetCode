package alg_02_train_dm._19_day_DFS_BFS_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-28 12:45
 * @Version 1.0
 */
public class _08_463_island_perimeter2 {

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
                    // 后续遍历
                    return dfs(row, col);
                }
            }
        }

        // KeyPoint 添加：返回语句 return
        // 虽然 for 循环中，已经有了 return，但是若 for 循环不执行，则方法就没有返回值，故编译报错
        // 所以需要在 for 循环的外面加一个 return 0，尽管不会执行该代码的
        return 0;
    }

    // KeyPoint 方法二 dfs => 后序遍历
    // 后序遍历：先不计算该节点 (row,col) 周长
    //          而是计算该节点 (row,col) 上下左右顶点的周长之和
    private int dfs(int row, int col) {

        // 直接将 if 判断条件拆分
        // if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col])
        // 不同情况，不同返回值

        // 上下左右节点中 => 越界 + 水域，则周长 1
        if (!inArea(row, col) || grid[row][col] == 0) return 1;

        // 上下左右节点中 => 该节点已经被访问，则周长 0
        if (visited[row][col]) return 0;

        // 先将其设置为被访问
        visited[row][col] = true;

        // 后序遍历：先不计算该节点 (row,col) 周长
        //          而是计算该节点 (row,col) 上下左右顶点的周长之和

        // KeyPoint 注意事项
        // 每个顶点局部定义 res，不能使用 前序遍历 全局定义的 res，否则导致 res 数值被重复计算
        int res = 0;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 递归计算上下左右节点周长，并将其累加 res，即为当前节点的周长
            res += dfs(nextRow, nextCol);
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
