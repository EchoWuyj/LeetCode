package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:35
 * @Version 1.0
 */

public class _10_162_find_peak_element {

    /*
        162. 寻找峰值
        峰值元素是指其值 严格大于左右相邻值的元素。

        给你一个整数数组 nums，找到峰值元素并返回其索引。
        数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

        你可以假设 nums[-1] = nums[n] = -∞ 。
        你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

        示例 1：
        输入：nums = [1,2,3,1]
        输出：2
        解释：3 是峰值元素，你的函数应该返回其索引 2。

        示例2：
        输入：nums = [1,2,1,3,5,6,4]
        输出：1 或 5
        解释：你的函数可以返回索引 1，其峰值元素为 2；
              或者返回索引 5， 其峰值元素为 6。

        提示：
        1 <= nums.length <= 1000
        -2^31 <= nums[i] <= 2^31 - 1
        对于所有有效的 i 都有 nums[i] != nums[i + 1]

        KeyPoint 分析
        注意：对于所有有效的 i 都有 nums[i] != nums[i + 1] => 相邻的元素不相等
              要么 nums[i] < nums[i + 1]，要么 nums[i] > nums[i + 1]，数列类似锯齿形排列
              ↗ ↘ ↗ ↘
         => 解题思路同 852
     */

    // KeyPoint 方法一 线性查找
    public int findPeakElement1(int[] nums) {
        // KeyPoint 存在 bug
        // i < nums.length - 1，i 最多为 i 为 nums.length-2，i 取不到 nums.length-1
        // 一般都是 i < nums.length
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                // 判断代码是否执行，通过输出语句
                // System.out.println(i);
                // KeyPoint 明确返回是索引值，还是数组元素，两者不要搞混淆了
                return i - 1;
        }
        // 若数列是严格升序，则返回最后一个索引位置
        // nums 只有一个元素时，返回 0
        return nums.length - 1;
    }

    // KeyPoint 方法二 二分查找
    // 给定数组没有严格有序，只是保证相邻的元素不相等，则可能有好几个山脉数组
    // ↗ ↘ ↗ ↘ (类似锯齿形排列)，但只要找到任何一个峰值接口，可以使用二分查找的
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // nums[mid] < nums[mid + 1] => 上坡，但找的是峰值，故排除 mid 以左，包括 mid
            // 注意：若数组后一半元素都是递增，最后峰值认为是 nums[nums.length-1]，
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                // nums[mid] >= nums[mid + 1]，
                right = mid;
            }
        }
        return left;
    }
}
