package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 10:29
 * @Version 1.0
 */
public class _01_643_maximum_average_subarray_i {
    public double findMaxAverage1(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
            System.out.println(maxSum);
        }
        return (double) maxSum / k;
    }

    public double findMaxAverage2(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(sum, maxSum);
            System.out.println(maxSum);
        }
        return (double) maxSum / k;
    }

    public double findMaxAverage3(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        while (right < n) {
            windowSum += nums[right];
            while (right - left + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return (double) maxSum / k;
    }
}
