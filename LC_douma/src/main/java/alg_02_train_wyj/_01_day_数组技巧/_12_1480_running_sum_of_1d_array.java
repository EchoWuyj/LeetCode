package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:15
 * @Version 1.0
 */
public class _12_1480_running_sum_of_1d_array {
    public int[] runningSum1(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            prefixSum[i] = sum;
        }
        return prefixSum;
    }

    public int[] runningSum2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;
    }

    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }
}
