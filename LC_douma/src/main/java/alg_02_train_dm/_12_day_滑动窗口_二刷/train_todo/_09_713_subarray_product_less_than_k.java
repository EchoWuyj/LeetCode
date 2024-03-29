package alg_02_train_dm._12_day_滑动窗口_二刷.train_todo;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 14:19
 * @Version 1.0
 */
public class _09_713_subarray_product_less_than_k {
    /*
        leetcode 713. 乘积小于K的子数组
        给定一个正整数数组 nums。

        找出该数组内乘积小于 k 的连续的子数组的个数。

        示例 1:
        输入: nums = [10,5,2,6], k = 100
        输出: 8
        解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
        需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
        说明:

        0 < nums.length <= 50000
        0 < nums[i] < 1000
        0 <= k < 10^6
     */

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        // 维护滑动窗口
        int left = 0, right = 0;
        // 存储乘积小于 k 的子数组的个数
        int ans = 0;
        // 当前窗口的所有元素的乘积
        int prod = 1;

        while (right < nums.length) {
            // 更新当前窗口的累计乘积
            prod *= nums[right];
            // left 指针移动
            // 移动时机：当前窗口累计乘积大于等于 k
            // 移动策略：将累计乘积除以需要移除的左边的元素值
            while (prod >= k) prod /= nums[left++];

            /*
            [10,5,2,6]
            第一个窗口 [10]      --> 符合条件的子数组：1
            第二个窗口 [10, 5]   --> 符合条件的子数组：[10, 5] 和 [5] 即 2 个 = right - left + 1
            ......
             */
            ans += right - left + 1;

            // right 指针移动
            right++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        new _09_713_subarray_product_less_than_k().numSubarrayProductLessThanK(nums, 1);
    }
}
