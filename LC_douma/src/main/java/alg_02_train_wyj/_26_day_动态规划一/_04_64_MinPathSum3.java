package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 18:08
 * @Version 1.0
 */
public class _04_64_MinPathSum3 {
    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return dfs(grid, 0, 0, memo);
    }

    public int dfs(int[][] grid, int i, int j, int[][] memo) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];
        int minPathSum = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (nexti == grid.length || nextj == grid[0].length) continue;
            int subMinPathSum = dfs(grid, nexti, nextj, memo);
            minPathSum = Math.min(minPathSum, subMinPathSum);
        }
        memo[i][j] = minPathSum + grid[i][j];
        return memo[i][j];
    }
}
