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
        int maxValue = nums[0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + nums[i];
            maxValue = Math.max(maxValue, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = dp[i - 1][j] - dp[i - 1][i - 1];
                maxValue = Math.max(maxValue, dp[i][j]);
            }
        }
        return maxValue;
    }

    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxValue = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
            maxValue = Math.max(maxValue, dp[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[j] = dp[j] - dp[i - 1];
                maxValue = Math.max(maxValue, dp[j]);
            }
        }
        return maxValue;
    }
}
