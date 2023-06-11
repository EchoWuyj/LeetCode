package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 20:56
 * @Version 1.0
 */
public class _06_53_MaxSubArray3 {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        int maxSum = dp[0];
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
