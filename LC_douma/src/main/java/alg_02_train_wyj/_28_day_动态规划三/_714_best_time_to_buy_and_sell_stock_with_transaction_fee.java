package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 21:49
 * @Version 1.0
 */
public class _714_best_time_to_buy_and_sell_stock_with_transaction_fee {
    public int maxProfit1(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[n - 1][0];
    }

    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], dp[0] - prices[i] - fee);
        }

        return dp[0];
    }
}
