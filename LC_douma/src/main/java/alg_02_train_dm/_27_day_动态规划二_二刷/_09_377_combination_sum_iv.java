package alg_02_train_dm._27_day_动态规划二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:29
 * @Version 1.0
 */
public class _09_377_combination_sum_iv {
       /*
            377. 组合总和 Ⅳ
            给你一个由 不同 整数组成的数组 nums，和一个目标整数 target
            请你从 nums 中找出并返回总和为 target 的元素组合的个数。

            KeyPoint 题目数据保证答案符合 32 位整数范围
                     => 数据是有效的，不会越界
                     => dp[j] 状态值不会数据溢出，从而保证状态转移方程不会出现错误

            示例 1：
            输入：nums = [1,2,3], target = 4
            输出：7
            解释：
            所有可能的组合为：
            (1, 1, 1, 1)
            (1, 1, 2)
            (1, 2, 1)
            (1, 3)
            (2, 1, 1)
            (2, 2)
            (3, 1)

            KeyPoint 注意：顺序不同的序列被视作不同的组合。

            示例 2：
            输入：nums = [9], target = 3
            输出：0

            提示：
            1 <= nums.length <= 200
            1 <= nums[i] <= 1000
            nums 中的所有元素 互不相同
            1 <= target <= 1000

     */

    // 完全背包问题
    // 在 nums 列表中，可重复的选择数字组合，使得组合之和等于 target
    public int combinationSum4(int[] nums, int target) {
        // 1. 状态定义：
        // dp[i]：能够凑成 target 为 i 的组合数
        int[] dp = new int[target + 1];

        // 2. 状态初始化
        // 凑成 target 为 0 的组合就是不选择任何整数 => 这也是一种组合
        dp[0] = 1;

        // 3. 状态转移
        // KeyPoint 注意
        // 本题不能直接使用 '完全背包' 模板代码，因为本题中 '顺序不同的序列被视作不同的组合'
        // 外层 for 循环是'从左往右'挑选数字，因此得到的组合也是有序的，则是有遗漏了乱序组合
        // 为了不会排除数字相同，但是顺序不同的组合，故调整内外层 for 循环顺序，从而避免有序

        // => 针对每一种 target (容量) 来选择所有的整数
        // => j 从 1 开始，没有交换之前 j = nums[i]，但是交换后，i 在内层，故 j 没法从 nums[i] 开始
        //    j = 1时，保证 dp[j - nums[i]] 不越界，通过 if (j >= nums[i]) 实现
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                // 注意：这里只是调整内外 for 循环，dp[j] 不用变成 dp[i]
                // dp[i] 定义为：凑成 target 为 i 的组合数，i 是关于 target 变化的
                if (j >= nums[i]) dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new _09_377_combination_sum_iv().combinationSum4(nums, 4));
    }
}
