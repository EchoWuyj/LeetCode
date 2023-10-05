package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 19:35
 * @Version 1.0
 */
public class _09_123_best_time_to_buy_and_sell_stock_iii {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];

        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        return dp[n - 1][2][0];
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[3][2];

        dp[1][0] = 0;
        dp[1][1] = -prices[0];
        dp[2][0] = 0;
        dp[2][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[1][0] = Math.max(dp[1][0], dp[1][1] + prices[i]);
            dp[1][1] = Math.max(dp[1][1], dp[0][0] - prices[i]);
            dp[2][0] = Math.max(dp[2][0], dp[2][1] + prices[i]);
            dp[2][1] = Math.max(dp[2][1], dp[1][0] - prices[i]);
        }
        return dp[2][0];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;

        int profit10 = 0;
        int profit11 = -prices[0];
        int profit20 = 0;
        int profit21 = -prices[0];

        for (int i = 1; i < n; i++) {
            profit10 = Math.max(profit10, profit11 + prices[i]);
            profit11 = Math.max(profit11, -prices[i]);
            profit20 = Math.max(profit20, profit21 + prices[i]);
            profit21 = Math.max(profit21, profit10 - prices[i]);
        }
        return profit20;
    }
}
