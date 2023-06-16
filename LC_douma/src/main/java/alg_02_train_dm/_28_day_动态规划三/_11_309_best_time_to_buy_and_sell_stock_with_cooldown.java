package alg_02_train_dm._28_day_动态规划三;

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
        在满足以下约束条件下，你可以尽可能地完成更多的交易(多次买卖一支股票)
            1. 你不能同时参与多笔交易(你必须在再次购买前出售掉之前的股票)
            2. 卖出股票后，你无法在第二天买入股票(即冷冻期为 1 天)
    
        示例:
        输入: [1,2,3,0,2]
        输出: 3
        解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

     */

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 原始代码： dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 其中 dp[i - 1][0] - prices[i] 即：在 i-1 天不持有股票，而是在 i 天买入股票

            // 本题：在有「冷却时间」的情况下，若在第 i - 1 天卖出了股票，则不能在第 i 天买入股票
            // 而若想在 i 天买入股票，则在第 i - 1 天不能卖出了股票

            // dp[i - 1][0] 不持有股票，是由两个状态决定的
            // => dp[i - 1][0] = Math.max(dp[i - 2][0], dp[i - 2][1] + prices[i]);
            //    dp[i - 2][0]：第 i-2 天不持有股票
            //    dp[i - 2][1] + prices[i]：第 i-2 天持有股票，并在 i-1 天卖出股票 × 不符合：第 i - 1 天不能卖出了股票
            //                              故将其排除
            // => dp[i - 1][0] = dp[i - 2][0] 即：第 i - 2 天不持有股票的最大利润

            // i >= 2 避免越界，遇到冷冻期，无法进行买卖，则利润为 0
            // 本质：因为冷冻期，dp[i - 1][0] 无法直接再由原先的状态转移方程得到，需要对状态转移方程进行限制
            // dp[i - 1][0] = Math.max(dp[i - 2][0], dp[i - 2][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 错误压缩状态，存在 bug
    public int maxProfit1(int[] prices) {

        // 状态转移方程，存在 dp[i] 和 dp[i - 1]，dp[i - 2] 关系，三者之间的关系
        // 不是想之前仅仅是 dp[i] 和 dp[i - 1] 关系(两行之间的关系)，故不能直接将横坐标抹去，需要使用变量来记录
//        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//        dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);

        int n = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], (i - 2 >= 0 ? dp[0] : 0) - prices[i]);
        }
        return dp[0];
    }

    // 正确状态压缩
    public int maxProfit2(int[] prices) {

        // prevProfit0 记录 dp[i - 2] 状态值
        // profit0 => dp[0]
        // profit1 => dp[1]

        // 本质：还是去掉横坐标，但是 dp[i-2] 不能直接抹掉，需要使用变量来保存

        int prevProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        int n = prices.length;
        for (int i = 1; i < n; i++) {
            int nextProfit0 = Math.max(profit0, profit1 + prices[i]);
            int nextProfit1 = Math.max(profit1, prevProfit0 - prices[i]);
            // 流程不是明白的话，看之前动态的压缩状态的视频
            prevProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }
}
