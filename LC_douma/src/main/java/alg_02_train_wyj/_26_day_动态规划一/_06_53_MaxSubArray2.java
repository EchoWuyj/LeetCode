package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 20:16
 * @Version 1.0
 */
public class _06_53_MaxSubArray2 {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        dp[0][0] = nums[0];
        int maxSum = dp[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = dp[i - 1][j] - dp[i - 1][i - 1];
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return maxSum;
    }
}
