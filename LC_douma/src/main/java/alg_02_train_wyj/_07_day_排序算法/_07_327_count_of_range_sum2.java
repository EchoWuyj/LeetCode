package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-07-10 12:39
 * @Version 1.0
 */
public class _07_327_count_of_range_sum2 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        long[] tmp = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return sort(prefixSum, 0, n, lower, upper, tmp);
    }

    public int sort(long[] prefixSum, int left, int right,
                    int lower, int upper, long[] tmp) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        int leftCount = sort(prefixSum, left, mid, lower, upper, tmp);
        int rightCount = sort(prefixSum, mid + 1, right, lower, upper, tmp);

        int count = 0;
        int i = left;
        int l = mid + 1, u = mid + 1;
        while (i <= mid) {
            while (l <= right && prefixSum[l] - prefixSum[i] < lower) l++;
            while (u <= right && prefixSum[u] - prefixSum[i] <= upper) u++;
            count += (u - l);
            i++;
        }
        merge(prefixSum, left, mid, right, tmp);
        return count + leftCount + rightCount;
    }

    public void merge(long[] prefixSum, int left, int mid, int right, long[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = prefixSum[i];
        }

        int i = left;
        int j = mid + 1;

        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                prefixSum[index] = tmp[j++];
            } else if (j == right + 1) {
                prefixSum[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                prefixSum[index] = tmp[i++];
            } else {
                prefixSum[index] = tmp[j++];
            }
        }
    }
}
