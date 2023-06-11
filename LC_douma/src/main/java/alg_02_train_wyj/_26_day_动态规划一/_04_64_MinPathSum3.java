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

    private int dfs(int[][] grid, int row, int col, int[][] memo) {
        if (row == grid.length-1 && col == grid[0].length-1) {
            return grid[row][col];
        }
        if (memo[row][col] != Integer.MAX_VALUE) return memo[row][col];

        int minPathSum = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextRow >= grid.length
                    || nextCol < 0 || nextCol >= grid[0].length) continue;
            int ChildMinPathSum = dfs(grid, nextRow, nextCol, memo);
            minPathSum = Math.min(minPathSum, ChildMinPathSum);
        }
        memo[row][col] = minPathSum + grid[row][col];
        return memo[row][col];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum3().minPathSum(grid));
    }
}
