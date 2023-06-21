package alg_02_train_dm._27_day_动态规划二_2刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:28
 * @Version 1.0
 */
public class _07_322_coin_change {
     /* 
        322. 零钱兑换
        给你一个整数数组 coins ，表示不同面额的硬币
        以及一个整数 amount ，表示总金额
        计算并返回可以凑成总金额所需的 最少的硬币个数
        你可以认为每种硬币的数量是无限的
        如果没有任何一种硬币组合能组成总金额，返回-1

        示例1：
        输入：coins = [1, 2, 5], amount = 11
        输出：3
        解释：11 = 5 + 5 + 1
    
        示例 2：
        输入：coins = [2], amount = 3
        输出：-1

        提示：
        1 <= coins.length <= 12
        1 <= coins[i] <= 2^31 - 1
        0 <= amount <= 104

        KeyPoint 零钱兑换 => 背包问题
        给你一个整数数组 coins ，表示不同面额的硬币    => 物品
        以及一个整数 amount ，表示总金额              => 背包容量
        计算并返回可以凑成总金额所需的 最少的硬币个数  => 目标
        你可以认为每种硬币的数量是无限的              => 背包类型：完全背包
        如果没有任何一种硬币组合能组成总金额，返回-1

     */

    // 零钱兑换转化为完全背包问题
    // => 从 coins 列表中，可重复选择最少数量的硬币，使得他们的总金额为 amount
    public int coinChange(int[] coins, int amount) {

        // 1. 状态定义
        // dp[i]：表示凑齐总金额为 i 时，需要的最小硬币数
        // 关于 金额 的 dp 数组
        int[] dp = new int[amount + 1];

        // 2. 状态初始化
        // 求最小值，需要初始化为：达不到的值 amount + 1
        // 表示此时总金额没有硬币凑齐，因为需要求最小的硬币数
        Arrays.fill(dp, amount + 1);

        // 注意：这里不能使用 Integer.MAX_VALUE
        // 因为若 dp[c - coins[i]] = Integer.MAX_VALUE，则 1 + dp[c - coins[i]] 会溢出

        // 凑齐总金额为 0 的时候需要的最小硬币数：不取硬币
        // 一般 dp[0] 是需要单独初始化
        dp[0] = 0;

        // 3. 状态转移
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 代码模板修改
                // 1.最小 => Math.min
                // 2.将 v[i] 替换成 1
                dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
        }
        // 总金额 amount 有可能凑不齐的，故需要判断下再去返回
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        System.out.println(new _07_322_coin_change().coinChange(coins, 3)); // -1
    }
}
