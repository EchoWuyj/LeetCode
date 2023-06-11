package alg_01_ds_wyj._01_line._05_algo._03_sort.train;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 16:03
 * @Version 1.0
 */
public class _05_Offer_51_ReversePairs {

    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }

    public int reversePairs2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = nums[i];
        }

        int[] tmp = new int[n];
        return mergeSort(copy, 0, n - 1, tmp);
    }

    public int mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;

        int leftCount = mergeSort(nums, left, mid, tmp);
        int rightCount = mergeSort(nums, mid + 1, right, tmp);
        int mergeCount = mergeAndCount(nums, left, mid, right, tmp);
        return leftCount + rightCount + mergeCount;
    }

    public int mergeAndCount(int[] data, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int count = 0;
        int i = left;
        int j = mid + 1;

        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                data[index] = tmp[j++];
            } else if (j == right + 1) {
                data[index] = tmp[i++];
            } else {
                if (tmp[i] <= tmp[j]) {
                    data[index] = tmp[i++];
                } else {
                    data[index] = tmp[j++];
                    count += mid - i + 1;
                }
            }
        }
        return count;
    }
}
