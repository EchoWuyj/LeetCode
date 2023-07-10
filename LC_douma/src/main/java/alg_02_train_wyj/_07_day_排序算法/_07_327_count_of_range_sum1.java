package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 12:51
 * @Version 1.0
 */
public class _07_327_count_of_range_sum1 {

    public int countRangeSum1(int[] nums, int lower, int upper) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int count = 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                long sum = prefixSum[j] - prefixSum[i];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }
}
