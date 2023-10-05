package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 19:42
 * @Version 1.0
 */
public class _09_377_combination_sum_iv {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }
}
