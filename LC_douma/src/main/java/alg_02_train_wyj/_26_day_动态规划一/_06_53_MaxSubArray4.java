package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 21:02
 * @Version 1.0
 */
public class _06_53_MaxSubArray4 {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        dp[0][0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = dp[0][j] - dp[0][i - 1];
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }
        return maxSum;
    }


    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[j] = dp[j] - dp[i - 1];
                maxSum = Math.max(maxSum, dp[j]);
            }
        }
        return maxSum;
    }
}


