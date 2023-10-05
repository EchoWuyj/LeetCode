package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 20:32
 * @Version 1.0
 */
public class _11_309_best_time_to_buy_and_sell_stock_with_cooldown {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;

        int preProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < n; i++) {
            int nextProfit0 = Math.max(profit0, profit1 + prices[i]);
            int nextProfit1 = Math.max(profit1, (i >= 2 ? preProfit0 : 0) - prices[i]);

            preProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }
}
