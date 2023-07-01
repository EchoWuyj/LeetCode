package alg_01_ds_wyj._01_line._05_algo._04_bs;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 14:41
 * @Version 1.0
 */
public class BinarySearch {

    public static int firstTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] != target) return mid;
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int lastTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int firstGETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                left = mid - 1;
            } else {
                right = mid + 1;
            }
        }
        return -1;
    }

    public static int lastLETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                if (mid == nums.length - 1 || nums[mid + 1] > target) return mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 5};
        int target = 3;

        System.out.println(firstTargetElement(arr, target)); // 2
        System.out.println(lastTargetElement(arr, target)); // 3
        System.out.println(firstGETargetElement(arr, target)); // 2
        System.out.println(lastLETargetElement(arr, target)); // 3
    }
}