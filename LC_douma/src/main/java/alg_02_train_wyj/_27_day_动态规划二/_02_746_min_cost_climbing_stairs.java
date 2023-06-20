package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 23:36
 * @Version 1.0
 */
public class _02_746_min_cost_climbing_stairs {

    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(cost, n, memo);
    }

    private int dfs(int[] cost, int n, int[] memo) {
        if (n == 0 || n == 1) return 0;
        if (memo[n] != Integer.MAX_VALUE) return memo[n];

        int left = cost[n - 1] + dfs(cost, n - 1, memo);
        int right = cost[n - 2] + dfs(cost, n - 2, memo);
        memo[n] = Math.min(left, right);

        return memo[n];
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
        }
        return dp[n];
    }

    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int prev = 0;
        int curr = 0;
        for (int i = 2; i <= n; i++) {
            int tmp = Math.min(cost[i - 1] + curr, cost[i - 2] + prev);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
