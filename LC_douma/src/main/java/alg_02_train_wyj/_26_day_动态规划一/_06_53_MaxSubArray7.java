package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 21:51
 * @Version 1.0
 */
public class _06_53_MaxSubArray7 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int dp = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            maxSum = Math.max(dp, maxSum);
        }
        return maxSum;
    }
}
