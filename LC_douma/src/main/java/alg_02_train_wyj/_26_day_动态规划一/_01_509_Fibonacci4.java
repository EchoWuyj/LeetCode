package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:11
 * @Version 1.0
 */
public class _01_509_Fibonacci4 {

    public int fib(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
