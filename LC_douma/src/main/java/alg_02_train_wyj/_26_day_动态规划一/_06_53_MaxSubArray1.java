package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 11:20
 * @Version 1.0
 */
public class _06_53_MaxSubArray1 {
    public int maxSubArray(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int sum = 0;
                for (int k = j; k <= i; k++) {
                    sum += nums[k];
                }
                maxValue = Math.max(maxValue, sum);
            }
        }
        return maxValue;
    }
}
