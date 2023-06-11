package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 12:51
 * @Version 1.0
 */
public class _07_327_count_of_range_sum {
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
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

    public int countRangeSum1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int count = 0;
        int m = prefixSum.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                long sum = prefixSum[j] - prefixSum[i];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int m = prefixSum.length;
        long[] tmp = new long[m];
        return mergeSort(prefixSum, 0, m - 1, lower, upper, tmp);
    }

    public int mergeSort(long[] prefixSum, int left, int right, int lower, int upper, long[] tmp) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int leftCount = mergeSort(prefixSum, left, mid, lower, upper, tmp);
        int rightCount = mergeSort(prefixSum, mid + 1, right, lower, upper, tmp);

        int count = 0;
        int l = mid + 1, u = mid + 1;
        int i = left;
        while (i <= mid) {
            while (l <= right && prefixSum[l] - prefixSum[i] < lower) l++;
            while (u <= right && prefixSum[u] - prefixSum[i] <= upper) u++;
            count += (u - l);
            i++;
        }
        merge(prefixSum, left, mid, right, tmp);
        return leftCount + rightCount + count;
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
            } else {
                if (tmp[i] <= tmp[j]) {
                    prefixSum[index] = tmp[i++];
                } else {
                    prefixSum[index] = tmp[j++];
                }
            }
        }
    }
}
