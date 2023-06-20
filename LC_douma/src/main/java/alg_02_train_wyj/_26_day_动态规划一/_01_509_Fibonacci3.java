package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:01
 * @Version 1.0
 */
public class _01_509_Fibonacci3 {
    public int fib(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    public int dfs(int n, int[] memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] != -1) return memo[n];

        int left = dfs(n - 1, memo);
        int right = dfs(n - 2, memo);
        memo[n] = left + right;
        return memo[n];
    }
}
