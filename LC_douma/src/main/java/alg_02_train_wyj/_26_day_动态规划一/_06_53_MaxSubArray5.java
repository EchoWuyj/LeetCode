package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 21:25
 * @Version 1.0
 */
public class _06_53_MaxSubArray5 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];

        int maxSum = prefixSum[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            maxSum = Math.max(maxSum, prefixSum[i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = prefixSum[j] - prefixSum[i - 1];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }
}
