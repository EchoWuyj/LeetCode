package alg_02_train_dm._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:29
 * @Version 1.0
 */
public class _08_518_coin_change_2 {
     /* 

        518. 零钱兑换 II

        给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额
        请你计算并返回可以凑成总金额的硬币组合数，如果任何硬币组合都无法凑出总金额，返回 0
        假设每一种面额的硬币有无限个。

        KeyPoint 题目数据保证结果符合 32 位带符号整数
                 => 数据是有效的，不会越界
                 => dp[j] 状态值不会数据溢出，从而保证状态转移方程不会出现错误

    
        示例 1：
        输入：amount = 5, coins = [1, 2, 5]
        输出：4
        解释：有四种方式可以凑成总金额：
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1
    
        示例 2：
        输入：amount = 3, coins = [2]
        输出：0
        解释：只用面额 2 的硬币不能凑成总金额 3

        提示：
        1 <= coins.length <= 300
        1 <= coins[i] <= 5000
        coins 中的所有值 互不相同
        0 <= amount <= 5000

     */

    // 转化为完全背包问题：
    // 从 coins 列表中，可重复选择硬币组合，使得他们的总金额为 amount，请问有多少种组合?
    public int change(int[] coins, int amount) {
        // 1. 状态定义
        // dp[c]：硬币列表能够凑成总金额为 c 的组合数
        int[] dp = new int[amount + 1];

        // 2. 状态初始化
        // 本题不是求最小值，故不需要如下初始化
        // Arrays.fill(dp, amount + 1);

        // 只要 dp[0] 单独初始化
        // 凑成总金额为 0 的组合就是不选择任何硬币，只有这一种组合，故组合数为 1
        dp[0] = 1;

        // 3. 状态转移
        // 1.外层 for 循环 => 遍历硬币数组，挨着选择硬币
        for (int i = 0; i < coins.length; i++) {
            // 2.内层 for 循环 => 遍历 amount 总金额，判断外层 for 循环选的硬币是否对解有优化
            for (int j = coins[i]; j <= amount; j++) {
                // 组合数，通过填表 => 总结出来
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
