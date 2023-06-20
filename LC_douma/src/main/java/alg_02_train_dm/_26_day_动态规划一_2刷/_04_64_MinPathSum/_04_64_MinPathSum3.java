package alg_02_train_dm._26_day_动态规划一_2刷._04_64_MinPathSum;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:03
 * @Version 1.0
 */
public class _04_64_MinPathSum3 {
    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        // 二维数组，存储行列(i,j) 到右下角(终点)的最小路径和
        int[][] memo = new int[grid.length][grid[0].length];
        // 遍历二维数组每行
        for (int i = 0; i < grid.length; i++) {
            // 对每行数组初始化赋值为 Integer.MAX_VALUE
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return dfs(grid, 0, 0, memo);
    }

    // 记忆化搜索
    private int dfs(int[][] grid, int row, int col, int[][] memo) {
        // 不是 grid.length，而是  grid.length-1
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        if (memo[row][col] != Integer.MAX_VALUE) return memo[row][col];
        int minPathSum = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextCol < 0
                    || nextRow >= grid.length
                    || nextCol >= grid[0].length) continue;
            int childMinPathSum = dfs(grid, nextRow, nextCol, memo);
            minPathSum = Math.min(minPathSum, childMinPathSum);
        }
        memo[row][col] = minPathSum + grid[row][col];
        return memo[row][col];
    }
}
