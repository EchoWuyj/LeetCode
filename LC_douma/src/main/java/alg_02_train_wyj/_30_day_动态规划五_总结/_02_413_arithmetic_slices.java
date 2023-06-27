package alg_02_train_wyj._30_day_动态规划五_总结;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 15:26
 * @Version 1.0
 */
public class _02_413_arithmetic_slices {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int[] dp = new int[n];
        dp[0] = dp[1] = 0;
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }
}
