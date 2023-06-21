package alg_02_train_dm._28_day_动态规划三_2刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 15:12
 * @Version 1.0
 */
public class _08_122_best_time_to_buy_and_sell_stock_ii {
    /* 
        122. 买卖股票的最佳时机 II
        给定一个数组 prices，其中 prices[i] 是一支给定股票第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易 (多次买卖一支股票)。
        注意：你不能同时参与多笔交易 (你必须在再次购买前出售掉之前的股票)。

        提示：
        1 <= prices.length <= 3 * 10^4
        0 <= prices[i] <= 10^4
     */

    /*
        通用状态转移方程
        dp[i][0][0] = 0
        dp[i][k][0] = max{dp[i-1][k][0],dp[i-1][k][1] + prices[i]}
        dp[i][k][1] = max{ dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]}

        集合本题条件
        当 k 无限次数，则 k-1 无限次数
        => 因为状态转移方程中，k 和 k-1 都是无限次数，故和 k 关系不大，统一将其省略

        dp[i][0][0] = 0
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
        dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
        注意：dp[i - 1][0] 不为 0，121 题将 k = 1 代入，从而得到 dp[i-1][0][0] = 0，再去消去维度 k

     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 初始化第一天
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    // 状态压缩(一)
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
        }
        return dp[0];
    }

    // 状态压缩(二)
    public int maxProfit3(int[] prices) {
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, profit0 - prices[i]);
        }

        return profit0;
    }

    // 使用贪心算法
    // => 因为本题交易次数是无限次，可以不考虑交易次数，
    //    只要股票价格上涨就是买入和卖出，从而保证最多获利
    public int maxProfit4(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            // 局部最优解 => 全局最优解
            // 每次都获得利润，没有利润遗漏，则获取的就是最多利润
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
