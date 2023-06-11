package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 18:41
 * @Version 1.0
 */
public class _04_64_MinPathSum4 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                } else if (i != m - 1 && j == n - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum4().minPathSum(grid));
    }
}
