package alg_03_leetcode_top_wyj.class_12;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 18:00
 * @Version 1.0
 */
public class Problem_0121_BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }
}
