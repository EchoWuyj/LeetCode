package alg_01_ds_dm._01_line._05_algo._04_bs;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 12:15
 * @Version 1.0
 */
public class _02_Advanced_BinarySearch {

    // KeyPoint 解决有序数组中存在'重复元素'问题
    // 正是因为有多个目标值，所以才有第一个，最后一个之分
    // 以下4个方法都是基于'思路一：在循环体内查找目标值'来实现

    // KeyPoint 查找第一个'等于'目标元素的下标
    public int firstTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // KeyPoint 代码规范:将 if 比较代码中 nums[] 放前面，target 放后面
            if (nums[mid] == target) {
                // 符合下面的两个条件之一就返回 mid ,否则去左边找：
                // 1 mid 是数组的第一个元素
                // 2 mid 前面的那个元素不等于 target
                // KeyPoint 涉及数组下标运算，一定需要考虑是否越界的情况，有效避免程序有 debug
                if (mid == 0 || nums[mid - 1] != target) return mid;
                    // mid 之前还有值等于 target，right 前移
                    // 左边还有，移动 right
                else right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // KeyPoint 查找最后一个'等于'目标元素的下标
    public int lastTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 符合下面的两个条件之一就返回 mid ：
                // 1. mid 是数组的最后一个元素
                // 2. mid 后面的那个元素不等于 target
                if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                    // mid 之后还有值等于 target，即右边还有，left 右移
                else left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // KeyPoint 查找第一个'大于等于'目标元素的下标
    //          '大于等于' 对应 '第一个'
    //          若不要求第一个，则大于等于 target 的元素在数组后面很多，算法就没有什么意义了
    //           ... [mid-2]，[mid-1]，[mid]
    //                                  >= target
    //           因为往前看，数列是降序，不能保证前面元素都是 >= target，故需要找第一个
    public int firstGETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // mid >= target，就需要判断之前元素，从而保证是第一个
            // 大于等于这种情况，if 判断中将 nums[mid] 放在前面
            if (nums[mid] >= target) {
                // 符合下面的两个条件之一就返回 mid
                // 1. mid 是数组的第一个元素
                // 2. mid 前面的那个元素小于 target
                // KeyPoint 保证 mid 是第一个 >= target，对立面为 mid-1 < target
                if (mid == 0 || nums[mid - 1] < target) return mid;
                    // mid 之前还有值小于等于 target，即左边还有，right 左移
                else right = mid - 1;
            } else { // target > nums[mid]
                left = mid + 1;
            }
        }
        return -1;
    }

    // KeyPoint 查找最后一个'小于等于'目标元素的下标
    //          '小于等于'对应'最后一个'
    //           若不要求最后一个，则小于等于 target 的元素在数组前面很多，算法就没有什么意义了
    //           [mid]，[mid+1]，[mid+2] ...
    //            <= target
    //           因为往后看，数列是升序，不能保证后续元素都是 <= target，故需要找最后一个
    public int lastLETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // mid <= target，就需要判断之后的元素，从而保证是最后一个
            if (nums[mid] <= target) {
                // KeyPoint 保证 mid 是最后一个 <= target，则需要 mid+1 > target
                if (mid == nums.length - 1 || nums[mid + 1] > target) return mid;
                    // mid 之后还有值小于等于 target，即右边还有， left 右移
                else left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }
        return -1;
    }
}
