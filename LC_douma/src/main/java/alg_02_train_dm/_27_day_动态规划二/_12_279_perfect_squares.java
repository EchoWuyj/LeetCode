package alg_02_train_dm._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 12:26
 * @Version 1.0
 */
public class _12_279_perfect_squares {
    
     /* 
        279. 完全平方数
        给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
        你需要让组成和的完全平方数的个数最少。
        给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
    
        完全平方数 是一个整数，其值等于另一个整数的平方；
        换句话说，其值等于一个整数自乘的积。
        例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
    
        示例 1：
        输入：n = 12
        输出：3
        解释：12 = 4 + 4 + 4
    
        示例 2：
        输入：n = 13
        输出：2
        解释：13 = 4 + 9
        提示：
        1 <= n <= 10^4

     */

    // 本质：零钱兑换 => 完全背包
    // 完全平方数最小为 1，最大为 sqrt(n) => 即从 nums = [1, 2, ..., sqrt(n)] 数组(物品)里选出几个数
    // 令其平方和为 n (背包容量)，求最少的完全平方数(目标)
    public int numSquares(int n) {
        // dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
        // dp 数组从 0 开始，且包括 n，故大小为 n+1
        int[] dp = new int[n + 1];

        // 若设置 dp[i] 为 Integer.MAX_VALUE，需要考虑状态转移方程中是否存在'数据溢出'
        // 如：dp[i] + 1 => Integer.MAX_VALUE + 1 => 数据溢出
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 另外一种形式也是可以的
        // Arrays.fill(dp, n + 1);
        dp[0] = 0;

        for (int i = 1; i <= Math.sqrt(n); i++) {
            // 完全背包，j >= i * i，保证 dp[j - i * i] 不越界
            // j 表示容量，j 可以取等 n
            for (int j = i * i; j <= n; j++) {
                // 选择一个数，故需要 +1
                // 对于每个 i，要么选择，要么不选，保证完全平方数的个数最少
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        // 本题不存在凑不成 n，因为完全平方数中有 1，必然能凑成 n，但不一定是最少数量
        return dp[n];
    }
}
