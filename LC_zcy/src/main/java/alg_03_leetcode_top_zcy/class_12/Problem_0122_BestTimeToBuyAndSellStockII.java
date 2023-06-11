package alg_03_leetcode_top_zcy.class_12;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:39
 * @Version 1.0
 */
public class Problem_0122_BestTimeToBuyAndSellStockII {
    
    /*
        给你一个整数数组prices,其中prices[i]表示某支股票第i天的价格.
        在每一天,你可以决定是否购买/出售股票(无限次交易).你在任何时候最多只能持有一股股票
        你也可以先购买,然后在同一天出售,返回你能获得的最大利润.
        
        
        输入:prices=[7,1,5,3,6,4]
        输出:7
        解释:在第2天(股票价格=1)的时候买入,在第3天(股票价格=5)的时候卖出,这笔交易所能获得利润=5-1=4。
        随后,在第4天(股票价格=3)的时候买入,在第5天(股票价格=6)的时候卖出,这笔交易所能获得利润=6-3=3。
        总利润为4+3=7。

     */

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            // 只要i位置满足[i-1]<[i],将[i]-[i-1]收益累加到ans中
            // 本质:将股票波动期间所有增长的收益分批进行了计算
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
