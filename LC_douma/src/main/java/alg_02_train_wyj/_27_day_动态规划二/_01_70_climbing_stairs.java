package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 23:07
 * @Version 1.0
 */
public class _01_70_climbing_stairs {

    public int climbStairs1(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    public int dfs(int n, int[] memo) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (memo[n] != -1) return memo[n];

        int left = dfs(n - 1, memo);
        int right = dfs(n - 2, memo);
        memo[n] = left + right;
        return memo[n];
    }

    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs3(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int pre = 1;
        int cur = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = pre + cur;
            pre = cur;
            cur = tmp;
        }

        return cur;
    }
}
