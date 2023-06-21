package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 19:35
 * @Version 1.0
 */
public class _10_188_best_time_to_buy_and_sell_stock_iv {
    public int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];

        for (int j = 1; j <= k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[k + 1][2];

        for (int j = 1; j <= k; j++) {
            dp[j][0] = 0;
            dp[j][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }
        return dp[k][0];
    }
}
