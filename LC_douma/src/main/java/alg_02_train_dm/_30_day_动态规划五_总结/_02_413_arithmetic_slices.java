package alg_02_train_dm._30_day_动态规划五_总结;

/**
 * @Author Wuyj
 * @DateTime 2023-06-16 19:48
 * @Version 1.0
 */
public class _02_413_arithmetic_slices {
     /*
        413. 等差数列划分
        如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。

        例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
        给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。

        子数组 是数组中的一个 连续序列。

        示例 1：
        输入：nums = [1,2,3,4]
        输出：3
        解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。

        示例 2：
        输入：nums = [1]
        输出：0

        提示：
        1 <= nums.length <= 5000
        -1000 <= nums[i] <= 1000

     */

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int res = 0;

        // dp[i]：表示数组区间 [0...i] 中等差数组的子数组个数
        // 注意：必须以 nums[i] 结尾，也就说必须选择 nums[i]
        int[] dp = new int[n];

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }

        return res;
    }
}
