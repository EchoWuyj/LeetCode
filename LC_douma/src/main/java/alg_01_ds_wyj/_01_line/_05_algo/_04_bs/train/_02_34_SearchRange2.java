package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-01 16:54
 * @Version 1.0
 */
public class _02_34_SearchRange2 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = getFirst(nums, target);
        int last = getLast(nums, target);
        return new int[]{first, last};
    }

    public int getFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[right] == target) return right;
        return -1;
    }

    public int getLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }
}
