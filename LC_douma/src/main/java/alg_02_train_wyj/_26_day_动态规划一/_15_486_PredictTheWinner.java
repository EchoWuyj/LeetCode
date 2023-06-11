package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 14:47
 * @Version 1.0
 */
public class _15_486_PredictTheWinner {

    public boolean PredictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1) >= 0;
    }

    private int dfs(int[] nums, int i, int j) {
        if (i == j) return nums[i];
        int left = nums[i] - dfs(nums, i + 1, j);
        int right = nums[j] - dfs(nums, i, j - 1);
        return Math.max(left, right);
    }

    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs1(nums, 0, n - 1, memo) >= 0;
    }

    public int dfs1(int[] nums, int i, int j, int[][] memo) {
        if (i == j) return nums[i];
        if (memo[i][j] != -1) return memo[i][j];

        int left = nums[i] - dfs1(nums, i + 1, j, memo);
        int right = nums[j] - dfs1(nums, i, j - 1, memo);
        memo[i][j] = Math.max(left, right);
        return memo[i][j];
    }

    public boolean PredictTheWinner3(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
