package alg_01_ds_dm._01_line._05_algo._04_bs_二刷.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 10:29
 * @Version 1.0
 */

public class _04_33_SearchInRotatedSortedArray {

    /*
        33. 搜索旋转排序数组
        整数数组 nums 按升序排列，数组中的值 互不相同 。

        在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
        使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
        （下标 从 0 开始 计数）。

        例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
                0 1 2 3 4 5 6
        给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
        则返回它的下标，否则返回 -1。

        你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

        提示：
        1 <= nums.length <= 5000
        -104 <= nums[i] <= 104
        nums 中的每个值都 独一无二
        题目数据保证 nums 在预先未知的某个下标上进行了旋转
        -104 <= target <= 104
    */


    /*
        KeyPoint 补充说明：旋转排序数组

        原始数组： 0 1 2 3 4 5 6
                  ↑
           将前面元素旋转到数组结尾

        有 2 种旋转情况

        1.旋转元素较少： 2 3 4 5 6 0 1
                                  ↑
                               旋转点

        2.旋转元素较多： 5 6 0 1 2 3 4
                            ↑
                         旋转点

        旋转数组特点
         1.前半部分有序，后半部分有序
         2.前半部分所有有序元素的值都 大于 后半部分所有有序元素的值
           => 针对部分有序，旋转数组查找目标元素，也可以使用二分查找
           => 二分查找，分成两部分区间 [left,mid] 或 [mid,right]，其中一个区间必然有序的
              value  4 5 6 7 0 1 2
              index  0 1 2 3 4 5 6
                     ↑     ↑     ↑
                   left   mid  right
                   nums[left] <= nums[right] =>  [left,mid] 区间有序

              1.nums[left] <= nums[mid]  => 符合常规 => 左边有序
                nums[left] = nums[mid]，看做只有一个元素，因此也是有序的
                6 9 10 11 13 18 1 3 4 5
                ↑         ↑           ↑
                left       mid        right
                |___有序___|

              2.nums[left] > nums[mid] => 区间异常 => 右边有序
                10 11 13 18 1 3 4 5 6 9
                ↑          ↑         ↑
                left       mid      right
                             |___有序___|
     */

    // 时间复杂度：O(logn)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;

        // 基于思路一
        // 在循环体内查找目标值来解决，左右两侧严格排除不存在的区间，直到 left == right
        // 只有一个元素，执行 while 循环体来判断是否为查找元素

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;

            // 使用 nums[left] 和 nums[mid] 进行比较
            // 1.为了先确定 左边 是有序区间，还是 右边 是有序区间
            // 2.之后再去确定 target 是在有序区间内，还是在有序区间外

            // 左边有序
            if (nums[left] <= nums[mid]) {
                // 判断 target 是否在左边有序区间内
                // target < nums[mid] 不用取等，上面一开始的 if 已经判断过了
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边有序
                // 判断 target 是否在右边有序区间内
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 经过一轮 while 循环，缩小一半区间，从而缩小搜索范围
        }
        // 最终没有在循环体内找到，则返回 -1
        return -1;
    }
}
