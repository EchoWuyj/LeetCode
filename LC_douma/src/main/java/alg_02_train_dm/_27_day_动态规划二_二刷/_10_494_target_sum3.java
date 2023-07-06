package alg_02_train_dm._27_day_动态规划二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-20 16:30
 * @Version 1.0
 */
public class _10_494_target_sum3 {

    // KeyPoint 方法四 0-1 背包问题 (二维状态数组实现) => 二维背包相关题目(重点)
    // 物品：数组中的元素
    // 背包容量：neg
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;

        // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
        // 求有多少组合数？

        // 1.状态定义
        // dp[i][c] 表示选择前 i 个数字组合，组合中所有数字之和为 c 的组合数
        // i 表示前 i 个数字，注意是个，所以 i 的取值范围为 [0...nums.length + 1]
        int[][] dp = new int[nums.length + 1][neg + 1];

        for (int i = 0; i <= neg; i++) {
            // 考虑不选择任何数字
            dp[0][i] = i == 0 ? 1 : 0;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= neg; j++) {
                int num = nums[i - 1];
                // 如果容量小于数字，那么表示不能选 num
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 这里 c >= num，有两种情况：
                    // 1.不选 num ，那么方案数为：dp[i - 1][c]
                    // 2.选 num，那么方案数为 dp[i - 1][c - num]
                    // 总方案数为 1+2
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                }
            }
        }
        return dp[nums.length][neg];
    }
}
