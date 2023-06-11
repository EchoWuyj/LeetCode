package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 14:51
 * @Version 1.0
 */
// 852. 山脉数组的峰顶索引
public class _06_852_PeakIndexInMountainArray {

    /*
        符合下列属性的数组 arr 称为 山脉数组 ：
        arr.length >= 3
        存在 i（0 < i < arr.length - 1）使得：
        arr[0] < arr[1] < ... arr[i-1] < arr[i]
        arr[i] > arr[i+1] > ... > arr[arr.length - 1]
        给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i]
         > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i

        KeyPoint 山脉数组不是严格有序，峰顶之前升序，峰顶之后降序 => 二分方法

     */

    // KeyPoint 方法一 暴力方法
    public int peakIndexInMountainArray1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }
        return nums.length - 1;
    }

    // KeyPoint 方法二 二分方法
    public int peakIndexInMountainArray(int[] nums) {
        // 山脉数组长度 > 3，判空条件可以省略
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 不断在循环体内，排除一定不存在目标元素的区间，最后只剩一个元素该元素就是峰顶
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 因为峰顶值并不知道的，故没有目标值 target，比较的条件，使用 [mid] 和 [mid+1]
            // nums[mid] < nums[mid + 1] => 上坡，但找的是峰值，故排除 mid 以左，包括 mid
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                // nums[mid] >= nums[mid + 1]，right 最多移动到 mid，nums[mid] 可能是最大值
                right = mid;
            }
        }
        return left;

        // KeyPoint 在力扣在线写代码，多使用自动的代码提示，提高写代码的效率，同时减少 bug
    }
}
