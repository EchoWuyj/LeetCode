package alg_02_train_dm._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-20 16:30
 * @Version 1.0
 */
public class _10_494_target_sum2 {

    // KeyPoint 方法三 0-1 背包问题 (一维状态数组实现)
    public int findTargetSumWays2(int[] nums, int target) {

        // 原问题：
        // 给你一个整数数组 nums 和一个整数 target
        // 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式
        // 求不同 表达式 的数目

        // 转化 0-1 背包问题
        // 假设数组中所有数字的总和为 sum (不考虑正负)
        // 若前面设置为负数的数字的总和是 neg(不含负号)，则设置为正数的数字的总和为 sum - neg
        // 根据 (sum - neg) - neg = target => neg = (sum - target) / 2

        // => 在数组 nums 列表中，不可重复的选择数字组合(每个数字选择一次 => 每个数字不可重复使用)，
        //     使得组合中所有数字之和为 neg (背包容量)，求有多少组合数(目标)？
        // => 0-1 背包问题

        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;

        // 1.若 diff < 0 => sum < target，则不存在 neg
        // 2.若 diff 为奇数，则在数学运算中 diff / 2 = 小数，在给定的整数数组中无法实现，则不存在 neg
        if (diff < 0 || diff % 2 == 1) return 0;

        // neg 不考虑负号，neg 单纯数值和
        int neg = diff / 2;

        // 0-1 背包问题
        // 在数组 nums 列表中，不可重复的选择数字组合，使得组合中所有数字之和为 neg
        // 求有多少组合数？

        // 区别：
        // => 本题：无序组合问题：5 = 2 + 2 + 1 或者 5 =  1 + 2 + 2，这两种组合，算作一种情况
        //    一般的 0-1 背包问题
        // => 377_combination_sum_iv target = 4，(2, 1, 1) 和 (2, 1, 1) 这两种组合，算作两种情况
        //    交换内外层 for 循环

        int[] dp = new int[neg + 1];
        // neg = 0，组合数为 1，即什么都不选择
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            // 0-1 背包问题 => 逆序遍历
            for (int j = neg; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[neg];
    }
}
