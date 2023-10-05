package alg_02_train_dm._28_day_动态规划三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 20:04
 * @Version 1.0
 */
public class _07_121_best_time_to_buy_and_sell_stock_推荐 {
    /*

        121. 买卖股票的最佳时机
        给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
        你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
        设计一个算法来计算你所能获取的最大利润。
        返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

        提示：
        1 <= prices.length <= 10^5
        0 <= prices[i] <= 10^4
     */

    /*
        通用状态转移方程
        dp[i][0][0] = 0
        dp[i][k][0] = max{dp[i-1][k][0],dp[i-1][k][1] + prices[i]}
        dp[i][k][1] = max{ dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]}

        结合本题条件，当 k = 1 时
        定义：dp[i][0][0] = 0
        dp[i][1][0] = max{dp[i-1][1][0],dp[i-1][1][1] + prices[i]}
        dp[i][1][1] = max{dp[i-1][1][1],dp[i-1][0][0] - prices[i]}，且 dp[i-1][0][0] = 0
                    = max{dp[i-1][1][1], - prices[i]}

        => k = 1，只是交易一次，不需要保存状态，可以压缩维度，不用单独设置一个维度
        dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        dp[i][1] = max(dp[i-1][1], dp[i-1][0] -prices[i])，且 dp[i-1][0][0] = 0
                 = max(dp[i-1][1], -prices[i])

        KeyPoint 注意事项
        1.不能直接将三维压成二维
          三维：dp[i][k][1] = max{dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]}
          二维：dp[i][1] = Math.max{dp[i-1][1], dp[i-1][0] - prices[i]};
        2.先将 k = 1 代入，再去三维压成二维
          将 k = 1 代入，dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
          且 dp[i-1][0][0] = 0 => dp[i][1][1] = Math.max(dp[i-1][1][1], -prices[i]);
          再三维压成二维，即：dp[i][1] = Math.max(dp[i-1][1],-prices[i]);

     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 初始化状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 状态转移方程
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        // 最后一天，不持有股票，为最大利润
        return dp[n - 1][0];
    }

    // 状态压缩(一)
    public int maxProfit2(int[] prices) {

        // 当天状态 dp[i] 只依赖于前一天 dp[i-1]
        // => 二维压缩一维
//        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//        dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);

        int n = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }

    // 状态压缩(二)
    public int maxProfit3(int[] prices) {

        // 将大小为 2 的数组，优化成两个变量，使用变量表示
        // 对照着状态压缩(一)代码，将 dp[0] 和 dp[1] 逐一替换

        // dp[0] => profit0
        // dp[1] => profit1

        int profit0 = 0;
        int profit1 = -prices[0];

        int n = prices.length;
        for (int i = 1; i < n; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, -prices[i]);
        }
        return profit0;
    }
}
