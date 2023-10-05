package alg_02_train_dm._28_day_动态规划三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:28
 * @Version 1.0
 */
public class _12_714_best_time_to_buy_and_sell_stock_with_transaction_fee {

     /* 
        714. 买卖股票的最佳时机含手续费
        给定一个整数数组  prices，其中第 i 个元素代表了第 i 天的股票价格 ；
        整数  fee 代表了交易股票的手续费用。
        你可以无限次地完成交易，但是你每笔交易都需要付手续费。
        如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
        返回获得利润的最大值。
        注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
    
        提示：
        1 <= prices.length <= 5 * 10^4
        1 <= prices[i] < 5 * 10^4
        0 <= fee < 5 * 10^4

     */

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        // KeyPoint 定义：买入股票支付一次手续费，而不是卖出股票时支付
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[n - 1][0];
    }

    // 状态压缩(一)
    public int maxProfit1(int[] prices, int fee) {
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

    // 状态压缩(二) 最优解
    public int maxProfit2(int[] prices, int fee) {
        int profit0 = 0;
        int profit1 = -prices[0] - fee;

        int n = prices.length;
        for (int i = 1; i < n; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, profit0 - prices[i] - fee);
        }

        return profit0;
    }
}
