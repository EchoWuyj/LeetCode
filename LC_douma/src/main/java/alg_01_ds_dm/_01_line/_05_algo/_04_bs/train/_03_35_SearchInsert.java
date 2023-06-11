package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 20:46
 * @Version 1.0
 */

// 35. 搜索插入位置
public class _03_35_SearchInsert {

    /*
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
        如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
        注意：nums 为 无重复元素 的 升序 排列数组
        KeyPoint 本质：找到第一个'大于等于' target 的元素的下标
     */

    // KeyPoint 思路一：在循环体内查找目标值
    // 找第一个'大于等于' target 的元素的下标
    public int searchInsert1(int[] nums, int target) {
        if (nums == null) return -1;
        // 判空条件有点不同，如果数组中一个元素都没有，则将 target 插入到第一个位置
        if (nums.length == 0) return 0;

        // KeyPoint 若 target > nums[nums.length-1]，直接返回 num.length 位置索引
        if (target > nums[nums.length - 1]) return nums.length;

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
        // return -1 是为了保证程序不出错，本题中 -1 是不会返回的
        // 因为有了 if (target > nums[nums.length - 1]) 进行兜底
        return -1;
    }

    // KeyPoint 思路二：在循环体内排除没有目标值的区间 => 重点掌握
    // 找第一个'大于等于' target 的元素的下标
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return 0;

        int left = 0;

        // KeyPoint 修改了 right 索引，变成了 nums.length，比原来 nums.length - 1 要多一位
        //          if (target > nums[nums.length - 1]) return nums.length; 将该种断情况进行合并了
        int right = nums.length;

        // left = right = nums.length，已经退出 while 循环了，
        // 所以不执里面代码，不会访问 nums[mid]，即不存在数组越界
        while (left < right) {
            int mid = left + (right - left) / 2;
            // target <= nums[mid] 反面 => target > nums[mid]
            if (target > nums[mid]) {
                left = mid + 1; // 严格排除一半区间
            } else {
                // target <= nums[mid]
                // 找'第一个' => 从右往左找
                right = mid;
            }
        }
        // 返回 left 就是第一个大于等于 target 的索引位置
        return left;
    }
}
