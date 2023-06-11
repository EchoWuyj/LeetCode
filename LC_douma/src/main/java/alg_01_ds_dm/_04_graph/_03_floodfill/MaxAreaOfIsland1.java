package alg_01_ds_dm._04_graph._03_floodfill;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 12:44
 * @Version 1.0
 */

// KeyPoint 思路:dfs(递归版本) => 重点掌握
//  本质 DFS 过程 => 一开始向着某个方向进行遍历，一旦某个方向没有路径，则换个方向，一直遍历下去
//                  直到遍历到没有任何方向有路径，此时进行回溯
//  优化:不用构建图，直接针对二维数组遍历，遍历到0，不用做任何事情，遇到1，再去遍历其上下左右
public class MaxAreaOfIsland1 {
    private int rows;
    private int cols;
    private int[][] grid;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;
        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;
        this.grid = grid;

        int res = 0;
        // 遍历二维数组中每个元素
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    res = Math.max(dfs(row, col), res);
                }
            }
        }
        return res;
    }

    // 外部调用 dfs 时，传入 row 和 col
    private int dfs(int row, int col) {
        // KeyPoint 访问过的顶点和值为 0 的顶点处理逻辑一样，
        //      直接略过不处理，故将其设置为 0，避免走回头路
        grid[row][col] = 0;
        int res = 1;
        // 上下左右 dfs
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (inArea(nextRow, nextCol)
                    && grid[nextRow][nextCol] == 1) {
                res += dfs(nextRow, nextCol);
            }
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIsland1 maxAreaOfIsland = new MaxAreaOfIsland1();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
