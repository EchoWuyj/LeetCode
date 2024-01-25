package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 12:53
 * @Version 1.0
 */
public class _17_121_maxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 不持有股票
        dp[0][0] = 0;
        // 持有股票
        dp[0][1] = -prices[0];

        // 一天一次交易
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // n 天，不持有股票
        return dp[n - 1][0];
    }
}
