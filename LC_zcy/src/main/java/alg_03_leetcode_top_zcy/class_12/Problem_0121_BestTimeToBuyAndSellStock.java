package alg_03_leetcode_top_zcy.class_12;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:38
 * @Version 1.0
 */

// 买卖股票的最佳时机
public class Problem_0121_BestTimeToBuyAndSellStock {
    /*
        给定一个数组prices,它的第i个元素prices[i]表示一支给定股票第i天的价格
        你只能选择某一天买入这只股票,并选择在未来的某一个不同的日子卖出该股票(只能一次交易)
        设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润
        如果你不能获取任何利润,返回0
     */

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 0...i 最小值
        int min = prices[0];
        // 若最后将ans进行return返回,一开始最好赋初值
        int ans = 0;
        // 枚举所有交易时机(每天股票价格),并取交易最大值
        for (int i = 0; i < prices.length; i++) {
            // 每天股票价格和min比较,取小值 => 最好的买入时机
            min = Math.min(min, prices[i]);
            // 每次交易值和ans比较,取大值 => 交易值最大
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }
}
