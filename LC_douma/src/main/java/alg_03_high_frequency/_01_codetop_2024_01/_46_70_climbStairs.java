package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 10:44
 * @Version 1.0
 */
public class _46_70_climbStairs {

    // 爬楼梯
    // 动态规划
    public int climbStairs(int n) {

        // 边界情况
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // i 从 3 开始，且 i 可以取到 n
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // n 阶楼梯
        return dp[n];
    }
}
