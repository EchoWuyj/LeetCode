package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 19:30
 * @Version 1.0
 */
public class _02_34_SearchRange1 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1,-1};
        int firstIndex = getFirstIndex(nums, target);
        int lastIndex = getLastIndex(nums, target);
        return new int[]{firstIndex, lastIndex};
    }

    public int getFirstIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] != target) return mid;
                else right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int getLastIndex(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                else left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
