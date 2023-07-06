package alg_02_train_dm._28_day_动态规划三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 15:56
 * @Version 1.0
 */
public class _10_188_best_time_to_buy_and_sell_stock_iv {
    /*
        188. 买卖股票的最佳时机 IV
        给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
        注意：你不能同时参与多笔交易(你必须在再次购买前出售掉之前的股票)。

        提示：
        0 <= k <= 100
        0 <= prices.length <= 1000
        0 <= prices[i] <= 1000
    */

    /*
        交易 k 次
        dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
        dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
        增加了：'交易次数' 和 '是否持有股票' 两个维度
     */

    // 执行用时：7 ms , 在所有 Java 提交中击败了22.23% 的用户
    public int maxProfit1(int k, int[] prices) {
        int n = prices.length;

        // 根据本题数据范围做判断，否则特殊的测试用例可能通过不了(本题不收影响)
        // 0 <= k <= 100
        // 0 <= prices.length <= 1000

        // k == 0 不做交易 或者 n = 1 没法交易 => 利润都是 0
        if (k == 0 || n < 2) return 0;

        // 若 k 非常大 (无限大)，k 交次数 >= 数组长度 / 2，相当于每天都是买入或卖出
        // 此时 k 使用不完，该问题变成了 122 号算法题，使用贪心求解
        // => 这种方式，若想不到就算了
        if (k >= n / 2) {
            return maxProfitGreedy(prices);
        }

        int[][][] dp = new int[n][k + 1][2];

        // 注意，j 从 1 开始，能取到 k，表示交易次数 1 到 k 次
        for (int j = 1; j <= k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }

        // 时间复杂度 O(n*k) => 10^5 可以接受
        for (int i = 1; i < n; i++) {
            // 注意：j 从 1 开始，j 能取到 k，若 j = 0，则 [j - 1] 越界
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][k][0];
    }

    // 贪心
    public int maxProfitGreedy(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    // 状态压缩
    // 执行用时：1 ms , 在所有 Java 提交中击败了 99.98% 的用户
    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (k == 0 || n < 2) return 0;

        if (k >= n / 2) {
            // 问题变成了 122 号算法题，相当于 k 无限大
            return maxProfitGreedy(prices);
        }

        int[][] dp = new int[k + 1][2];

        for (int j = 1; j <= k; j++) {
            dp[j][0] = 0;
            dp[j][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }

        return dp[k][0];
    }
}
