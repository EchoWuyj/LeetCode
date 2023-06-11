package algorithm._11_dynamic_programming;

/**
 * @Author Wuyj
 * @DateTime 2022-03-28 20:49
 * @Version 1.0
 */
public class LeetCode_121_BestTimeToBuyAndSellStock {
    //方法一:暴力法
    public int maxProfit01(int[] prices) {
        int maxProfit = 0;
        //遍历所有可能的买入卖出情况
        //最后一天买入相当于利润为0,所以for循环可以跳过最后一个元素
        for (int i = 0; i < prices.length - 1; i++) {
            //卖出的时间点的遍历
            for (int j = i; j < prices.length; j++) {
                //利润=卖出时间-买入时间
                int curProfit = prices[j] - prices[i];
                //获取最大利润
                maxProfit = Math.max(maxProfit, curProfit);
            }
        }
        return maxProfit;
    }

    //方法二:动态规划
    public int maxProfit02(int[] prices) {
        //定义状态:保存到目前为止的最小价格,和最大利润
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        //遍历数组元素,以当前价格为卖出点进行比较
        //最后一个节点也是需要进行遍历的,和最小值没有关系,但是和卖出节点有关
        for (int i = 0; i < prices.length; i++) {
            //在之前的最小值和当前的值中取最小值,作为买入点更新
            minPrice = Math.min(minPrice, prices[i]);
            //在之前的最大利润和当前的利润中取最大值,作为卖出点更新
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        LeetCode_121_BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new LeetCode_121_BestTimeToBuyAndSellStock();

        System.out.println(bestTimeToBuyAndSellStock.maxProfit02(prices1));
        System.out.println(bestTimeToBuyAndSellStock.maxProfit02(prices2));
    }
}

