package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 12:53
 * @Version 1.0
 */
public class _17_121_maxProfit {

    // 买卖股票的最佳时机
    // 动态规划
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 不持有股票
        dp[0][0] = 0;
        // 持有股票
        dp[0][1] = -prices[0];

        // 一天一次交易
        // 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票
        // i 从 1 开始
        for (int i = 1; i < n; i++) {
            // | 前一天 相同状态 | 在前；| 前一天 不同状态 | 在后
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 两行代码都是 prices[i]，只不过一个是 +，另外一个是 -
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // n 天，不持有股票
        return dp[n - 1][0];
    }
}
