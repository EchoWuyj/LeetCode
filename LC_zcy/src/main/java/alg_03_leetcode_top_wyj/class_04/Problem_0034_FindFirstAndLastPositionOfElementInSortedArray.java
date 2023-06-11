package alg_03_leetcode_top_wyj.class_04;

/**
 * @Author Wuyj
 * @DateTime 2023-02-19 13:45
 * @Version 1.0
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }
        int start = BSNearLeft(nums, target);
        int end = BSNearRight(nums, target);
        return new int[]{start, end};
    }

    public int BSNearLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        int index = -1;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                index = mid;
                r = mid - 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    public int BSNearRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        int index = -1;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] == target) {
                index = mid;
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }
}
