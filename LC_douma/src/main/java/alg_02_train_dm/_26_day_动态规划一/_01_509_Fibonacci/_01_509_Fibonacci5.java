package alg_02_train_dm._26_day_动态规划一._01_509_Fibonacci;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:32
 * @Version 1.0
 */
public class _01_509_Fibonacci5 {
    // 动态规划 4 个步骤
    public int fib(int n) {
        if (n <= 1) return n;

        // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
        // 定义状态数组 => 动态规划难点1
        int[] dp = new int[n + 1];

        // 2. 状态初始化
        dp[0] = 0;
        dp[1] = 1;

        // 3. 状态转移
        // 状态转移方程 => 动态规划难点2
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回最终需要的状态值
        return dp[n];
    }
}
