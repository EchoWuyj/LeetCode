package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 21:05
 * @Version 1.0
 */
public class _03_035_SearchInsert {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (target > nums[nums.length - 1]) return nums.length;
        return left;
    }

    public int searchInsert1(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (target > nums[nums.length - 1]) return nums.length;
        return -1;
    }
}
