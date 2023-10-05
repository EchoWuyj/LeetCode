package alg_02_train_dm._28_day_动态规划三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:21
 * @Version 1.0
 */
public class _11_309_best_time_to_buy_and_sell_stock_with_cooldown {
    /* 
        309. 最佳买卖股票时机含冷冻期
        给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格。
        设计一个算法计算出最大利润。
        在满足以下约束条件下，你可以尽可能地完成更多的交易(多次买卖一支股票) => 无限次数
            1. 你不能同时参与多笔交易(你必须在再次购买前出售掉之前的股票)
            2. 卖出股票后，你无法在第二天买入股票(即冷冻期为 1 天)
    
        示例:
        输入: [1,2,3,0,2]
        输出: 3
        解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);

            // 原始代码： dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 其中 dp[i - 1][0] - prices[i] 即：在 i-1 天不持有股票，而是在 i 天买入股票时的状态
            // 但本题对'买入股票时间'有限制，则之前的状态转移方程不行

            // 本题：在有「冷却时间」的情况下，若在第 i - 1 天卖出了股票，则不能在第 i 天买入股票
            // 而若想在 i 天买入股票，则在第 i - 1 天不能卖出了股票。

            // dp[i - 1][0] 表示不持有股票，是由两个状态决定的
            // => dp[i - 1][0] = Math.max(dp[i - 2][0], dp[i - 2][1] + prices[i]);
            //    dp[i - 2][0]：第 i-2 天不持有股票
            //    dp[i - 2][1] + prices[i]：第 i-2 天持有股票，并在 i-1 天卖出股票
            //                              => 这种情况不符合：第 i-1 天不能卖出了股票限制，故将其排除
            // => dp[i - 1][0] = dp[i - 2][0] 即：第 i - 2 天不持有股票的最大利润

            // KeyPoint 附加条件：i >= 2 避免越界，遇到冷冻期，无法进行买卖，则利润为 0

            // KeyPoint 本质
            // 因为有冷冻期限制，dp[i - 1][0] 无法直接再由原先的状态转移方程得到，需要对状态转移方程进行修改
            // 结合题目限制条件，从而选择其中一个转态转移方程
            // dp[i - 1][0] = Math.max(dp[i - 2][0], dp[i - 2][1] + prices[i]);
        }
        return dp[n - 1][0];
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {

//        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//        dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);

        // KeyPoint 注意事项
        // 状态转移方程，存在 dp[i] 和 dp[i-1]，dp[i-2] 关系，三者之间的关系
        // 不是想之前仅仅是 dp[i] 和 dp[i-1] 关系，故不能直接将横坐标 i 抹去，需要使用变量来记录

        // 本质：还是去掉横坐标，但是 dp[i-2] 不能直接抹掉，需要使用变量来保存
        // 三者之间的关系 dp[i] 和 dp[i-1]，dp[i-2] 关系，使用三个变量来记录
        // prevProfit0 => dp[i-2]
        // profit0 => dp[0]
        // profit1 => dp[1]

        int prevProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        int n = prices.length;
        for (int i = 1; i < n; i++) {
            int nextProfit0 = Math.max(profit0, profit1 + prices[i]);
            int nextProfit1 = Math.max(profit1, prevProfit0 - prices[i]);

            prevProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }
}
