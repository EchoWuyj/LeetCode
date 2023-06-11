package alg_03_leetcode_top_wyj.class_12;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 18:32
 * @Version 1.0
 */
public class Problem_0122_BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += (prices[i] > prices[i - 1]) ? prices[i] - prices[i - 1] : 0;
        }
        return ans;
    }
}
