package alg_02_train_dm._21_day_综合应用二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:37
 * @Version 1.0
 */
public class _10_421_maximum_xor_of_two_numbers_in_an_array1 {
     /*
        421. 数组中两个数的最大异或值
        给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，
        其中 0 ≤ i ≤ j < n 。

        进阶：你可以在 O(n) 的时间解决这个问题吗？

        示例 1：
        输入：nums = [3,10,5,25,2,8]
        输出：28
        解释：最大运算结果是 5 XOR 25 = 28.

        提示：
        1 <= nums.length <= 2 * 10^5
        0 <= nums[i] <= 2^31 - 1

        KeyPoint 补充说明：
        在 Java 中，两个大于等于0的数异或的结果是一个正数，除非这两个数相等，此时异或的结果为0。

        异或运算符（^）用于比较两个二进制数的每一位，如果相同则为0，不同则为1。
        因此，对于两个大于等于0的二进制数，至少有一个位不同，所以它们的异或结果是一个正数。

        例如，对于两个正整数 10 和 25，它们的异或结果为：10 ^ 25 = 31
        这是因为它们的二进制表示分别为：1010 和 10101001
              0000 1010
            ^ 1010 1001
            ------------
              1010 0011
        至少有一个位不同，因此异或结果为31（二进制表示为 1010|1011）

     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int findMaximumXOR1(int[] nums) {
        // 数组只有一个元素，默认异或结果为 0
        // 注意：该输出是测试用例给出来的，题目没有说清楚
        int n = nums.length;
        if (n < 2) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 优化：线性查找
                maxValue = Math.max(maxValue, nums[i] ^ nums[j]);
            }
        }
        return maxValue;
    }
}


