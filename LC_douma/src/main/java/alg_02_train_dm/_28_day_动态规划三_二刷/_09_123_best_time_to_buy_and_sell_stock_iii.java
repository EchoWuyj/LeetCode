package alg_02_train_dm._28_day_动态规划三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 15:40
 * @Version 1.0
 */
public class _09_123_best_time_to_buy_and_sell_stock_iii {
      /* 
        123. 买卖股票的最佳时机 III
        给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格
        设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易
        注意：你不能同时参与多笔交易(你必须在再次购买前出售掉之前的股票)

        提示：
        1 <=prices.length <= 10^5
        0 <=prices[i] <=10^5

     */

    /*
        通用状态转移方程
        dp[i][0][0] = 0
        dp[i][k][0] = max{dp[i-1][k][0],dp[i-1][k][1] + prices[i]}
        dp[i][k][1] = max{ dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]}

        特别注意
        => 在买卖股票中，定义买入操作算作一次交易，而卖出不算一次交易
        => 注意：k-1 的原因：买入操作会使用一次交易

        结合本题条件，当 k 最多 2 时，分别 k = 1 和 k = 2，分别进行列举
        dp[i][0][0] = 0
        dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
        dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
        dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])

     */

    // 执行用时：70 ms , 在所有 Java 提交中击败了 14.81%% 的用户
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // KeyPoint 2 次交易，数组大小定义为 3
        // 0 次交易，1 次交易，2 次交易
        int[][][] dp = new int[n][3][2];

        // 初始化状态数组，第一天 4 种状态
        // => 只要不持股票利润为 0，持股票利润为 - prices[0]
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            // 这里因为 k = 2，k 比较小，通过枚举的方式列出
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        // n-1 天，最多 2 交易，不持有股票 => 最大利润
        return dp[n - 1][2][0];
    }

    // 状态压缩 => 推荐使用，能大幅提高性能
    // 执行用时：3 ms , 在所有 Java 提交中击败了 74.92% 的用户
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

    // 进一步 状态压缩 => 性能最好
    // 执行用时：1 ms , 在所有 Java 提交中击败了 100.00% 的用户
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
