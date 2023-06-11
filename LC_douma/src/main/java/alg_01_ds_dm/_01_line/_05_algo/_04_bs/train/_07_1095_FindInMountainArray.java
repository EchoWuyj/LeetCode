package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 15:30
 * @Version 1.0
 */
public class _07_1095_FindInMountainArray {

    public int findInMountainArray(int target, int[] nums) {

        // KeyPoint 注意:山脉数组长度 > 3，判空条件可以省略
        //          力扣上形参 MountainArray nums，需要调用相应的方法，不能使用 int[]

        // 1. 找到峰顶元素索引
        int peakIndex = searchPeakIndex(nums);
        // 2. 在前半部分应用二分查找算法查找目标值
        int index = binarySearchFrontPart(nums, 0, peakIndex, target);
        if (index != -1) {
            return index;
        }
        // 3. 在后半部分应用二分查找算法查找目标值
        return binarySearchLatterPart(nums, peakIndex, nums.length - 1, target);
    }

    // 1. 找到峰顶元素索引
    private int searchPeakIndex(int[] nums) {
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

    // 2. 在前半部分，升序序列，二分查找
    private int binarySearchFrontPart(int[] nums, int left, int peakIndex, int target) {
        while (left < peakIndex) {
            int mid = left + (peakIndex - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                peakIndex = mid;
            }
        }
        if (nums[left] == target) return left;
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

    // 3. KeyPoint 在后半部分，降序序列，变形二分查找 => 不能单纯地复制升序序列代码
    private int binarySearchLatterPart(int[] nums, int peakIndex, int right, int target) {
        while (peakIndex < right) {
            int mid = peakIndex + (right - peakIndex) / 2;
            if (target < nums[mid]) {
                peakIndex = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[peakIndex] == target) return peakIndex;
        return -1;
    }

    private static int BSLatterPart(int[] nums, int peakIndex, int lastIndex, int target) {
        int left = peakIndex;
        int right = lastIndex;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                // KeyPoint 降序序列 => 只需要将 else if 和 else 代码对调即可
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
