package alg_03_leetcode_top_zcy.class_12;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:39
 * @Version 1.0
 */

// 买卖股票的最佳时机 III
public class Problem_0123_BestTimeToBuyAndSellStockIII {
    
    /*
        给定一个数组,它的第i个元素是一支给定的股票在第i天的价格
        设计一个算法来计算你所能获取的最大利润,你最多可以完成两笔交易
        注意:你不能同时参与多笔交易(你必须在再次购买前出售掉之前的股票)
     */

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int ans = 0;
        int doneOnceMinusBuyMax = -prices[0];
        int doneOnceMax = 0; // 0 : [0] - [0]
        int min = prices[0];
        // i位置需要做完两次交易,且第二次交易卖出时机在i位置
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            min = Math.min(min, prices[i]);
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
        }
        return ans;
    }
}
