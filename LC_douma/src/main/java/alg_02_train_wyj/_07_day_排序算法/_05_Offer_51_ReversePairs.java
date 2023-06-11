package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 20:20
 * @Version 1.0
 */
public class _05_Offer_51_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] tmp = new int[n];
        return mergeSort(nums, 0, n - 1, tmp);
    }

    public int mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int leftCount = mergeSort(nums, left, mid, tmp);
        int rightCount = mergeSort(nums, mid + 1, right, tmp);
        int mergeCount = merge(nums, left, mid, right, tmp);
        return leftCount + rightCount + mergeCount;
    }

    public int merge(int[] nums, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j++];
            } else if (j == right + 1) {
                nums[index] = tmp[i++];
            } else {
                if (tmp[i] <= tmp[j]) {
                    nums[index] = tmp[i++];
                } else {
                    nums[index] = tmp[j++];
                    count += mid - i + 1;
                }
            }
        }
        return count;
    }
}
