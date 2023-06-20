package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-19 14:35
 * @Version 1.0
 */
public class _15_486_PredictTheWinner2 {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(nums, 0, n - 1, memo) >= 0;
    }

    public int dfs(int[] nums, int i, int j, int[][] memo) {
        if (i == j) return nums[i];
        if (memo[i][j] != -1) return memo[i][j];
        int left = nums[i] - dfs(nums, i + 1, j, memo);
        int right = nums[j] - dfs(nums, i, j - 1, memo);
        memo[i][j] = Math.max(left, right);
        return memo[i][j];
    }
}
