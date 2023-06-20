package alg_02_train_dm._26_day_动态规划一_2刷._01_509_Fibonacci;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 12:56
 * @Version 1.0
 */
public class _01_509_Fibonacci3 {

    public int fib(int n) {
        // 0 <= n <= 30，数据范围可控，使用数组代替 Map，数组索引作为 n
        // 0 ~ 30，一共是 31 个元素，故初始化数组大小为 n+1
        int[] memo = new int[n + 1];
        // 初始化 -1，避免和 Fib 中 0 相混淆
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    // 记忆化搜索 (自顶向下)
    // 时间复杂度：O(n)
    private int dfs(int n, int[] memo) {

        if (n == 0) return 0;
        if (n == 1) return 1;

        // 上面代码，可以简写
        // if (n == 0 || n == 1) return n;

        if (memo[n] != -1) {
            return memo[n];
        }
        int leftFib = dfs(n - 1, memo);
        int rightFib = dfs(n - 2, memo);

        memo[n] = leftFib + rightFib;

        return leftFib + rightFib;
    }
}
