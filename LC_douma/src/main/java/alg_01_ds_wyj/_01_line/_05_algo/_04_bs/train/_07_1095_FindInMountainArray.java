package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 15:41
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray {
    public static int findInMountainArray(int target, int[] nums) {
        int peakIndex = searchPeakIndex(nums);
        int index = BSFrontPart(nums, 0, peakIndex, target);
        if (index != -1) {
            return index;
        }
        return BSLastPart(nums, peakIndex, nums.length - 1, target);
    }

    private static int BSLastPart(int[] nums, int peakIndex, int lastIndex, int target) {
        int left = peakIndex;
        int right = lastIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int BSFrontPart(int[] nums, int i, int peakIndex, int target) {
        int left = i;
        int right = peakIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int searchPeakIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2};
        System.out.println(findInMountainArray(2, arr));
    }
}
