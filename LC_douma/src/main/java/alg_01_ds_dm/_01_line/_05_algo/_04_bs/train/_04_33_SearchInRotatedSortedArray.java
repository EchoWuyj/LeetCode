package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 10:29
 * @Version 1.0
 */

// 33. 搜索旋转排序数组
public class _04_33_SearchInRotatedSortedArray {

    /*
           4 5 6 7 0 1 2
                   ↑
                 旋转点

           旋转数组特点
           1 前半部分有序，后半部分有序
           2 前半部分所有有序元素的值都大于后半部分所有有序元素的值
           => 部分有序旋转数组查找目标元素，也可以使用二分查找
     */

    // 时间复杂度：O(logn)
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 基于思路一即在循环体内查找目标值来解决，直到 left == right
        // 只有一个元素，执行 while 循环体来判断是否为查找元素
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;

            //  KeyPoint 使用 nums[left] 和 nums[mid] 进行比较，为了先确定左边是有序区间
            //           还是右边是有序区间，之后再去确定 target 是在有序区间内，还是在有序区间外

            // 1 nums[left] <= nums[mid] 左边有序
            //   nums[left] = nums[mid]，看做只有一个元素，因此也是有序的
            //   6 9 10 11 13 18 1 3 4 5
            //   ↑         ↑           ↑
            //  left       mid        right
            //   |___有序___|
            if (nums[left] <= nums[mid]) {
                // 判断 target 是否在左边有序区间内
                // target < nums[mid] 不用取等，上面 if 已经判断过了
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                // 2 nums[left] > nums[mid] 右边有序
                //  10 11 13 18 1 3 4 5 6 9
                //   ↑          ↑         ↑
                //  left       mid      right
                //              |___有序___|
            } else {
                // 判断 target 是否在右边有序区间内
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
