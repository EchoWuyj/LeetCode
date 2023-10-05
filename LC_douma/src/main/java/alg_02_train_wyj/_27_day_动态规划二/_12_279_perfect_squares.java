package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 20:19
 * @Version 1.0
 */
public class _12_279_perfect_squares {
    public int numSquares(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
}
