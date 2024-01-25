package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _75_34_searchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int firstIndex = getFirstIndex(nums, target);
        int lastIndex = getLastIndex(nums, target);
        return new int[]{firstIndex, lastIndex};
    }

    private int getFirstIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (nums[left] == target) return left;
        return -1;
    }

    private int getLastIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
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
