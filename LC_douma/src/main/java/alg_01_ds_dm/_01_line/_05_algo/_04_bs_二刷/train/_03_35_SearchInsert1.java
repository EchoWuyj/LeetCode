package alg_01_ds_dm._01_line._05_algo._04_bs_二刷.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 20:46
 * @Version 1.0
 */

public class _03_35_SearchInsert1 {

    /*
        35. 搜索插入位置
        给定一个 排序数组 和一个目标值，在数组中找到目标值，并返回其索引。
        如果目标值不存在于数组中，返回它将会被 按顺序插入的位置。
        注意：nums 为 无重复元素 的 升序 排列数组
        请必须使用时间复杂度为 O(log n) 的算法。

        KeyPoint => 本质：找到第一个'大于等于' target 的元素的下标

        示例 1:
        输入: nums = [1,3,5,6], target = 5
        输出: 2

        示例2:
        输入: nums = [1,3,5,6], target = 2
        输出: 1

        提示:
        1 <= nums.length <= 104
        -104 <= nums[i] <= 104
        nums 为无重复元素的升序排列数组
        -104 <= target <= 104

     */

    // KeyPoint 思路一：在循环体内查找目标值
    //  => 找第一个'大于等于' target 的元素的下标
    public int searchInsert1(int[] nums, int target) {
        if (nums == null) return -1;
        // 判空条件有点不同，如果数组中一个元素都没有，则将 target 插入到第一个位置
        if (nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 写'大于等于'代码，将中点值 [mid] 放在前面
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 若 target > nums[nums.length-1]，直接返回 num.length 位置索引
        if (target > nums[nums.length - 1]) return nums.length;

        // return -1 是为了保证程序不出错，本题中 -1 是不会返回的
        // 因为有了 if (target > nums[nums.length - 1]) 进行兜底
        return -1;
    }
}
