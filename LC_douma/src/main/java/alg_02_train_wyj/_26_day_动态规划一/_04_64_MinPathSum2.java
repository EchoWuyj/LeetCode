package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 16:50
 * @Version 1.0
 */
public class _04_64_MinPathSum2 {

    private int[][] dirs = {{1, 0}, {0, 1}};

    public int minPathSum(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    public int dfs(int[][] grid, int i, int j) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }

        int minPathSum = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (nexti == grid.length || nextj == grid[0].length) continue;
            int subMinPathSum = dfs(grid, nexti, nextj);
            minPathSum = Math.min(minPathSum, subMinPathSum);
        }
        return minPathSum + grid[i][j];
    }
}
