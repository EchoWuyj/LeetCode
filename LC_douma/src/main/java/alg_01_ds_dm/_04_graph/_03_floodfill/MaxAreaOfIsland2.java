package alg_01_ds_dm._04_graph._03_floodfill;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-23 12:45
 * @Version 1.0
 */
// KeyPoint 思路:dfs(迭达版本)
public class MaxAreaOfIsland2 {
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
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    // 统计值为 1 的个数
                    int currOnes = 0;
                    // 每个元素下标索引 (row,col)，故使用数组来存
                    ArrayDeque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{row, col});
                    // KeyPoint bug 修复，每次将元素 push 到栈中都要将其设置为 0
                    grid[row][col] = 0;
                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        int currRow = curr[0], currCol = curr[1];
                        currOnes++;
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];
                            if (inArea(nextRow, nextCol)
                                    && grid[nextRow][nextCol] == 1) {
                                stack.push(new int[]{nextRow, nextCol});
                                // KeyPoint bug 修复，每次将元素 push 到栈中都要将其设置为 0
                                grid[nextRow][nextCol] = 0;
                            }
                        }
                    }
                    // 该代码块在 if 判断里面， 表示值为 1 周围的一次 dfs
                    // dfs 结束之后和 res 比较，取最大值
                    res = Math.max(res, currOnes);
                }
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
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIsland2 maxAreaOfIsland = new MaxAreaOfIsland2();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
