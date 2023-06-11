package alg_02_train_dm._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:30
 * @Version 1.0
 */
public class _10_494_target_sum {
     /*
        494.目标和
        给你一个整数数组 nums 和一个整数 target 。
        向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

        例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，
        然后串联起来得到表达式 "+2-1 = 1" 。
        返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

        示例 1：
        输入：nums = [1,1,1,1,1], target = 3
        输出：5
        解释：一共有 5 种方法让最终目标和为 3 。
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3

        提示：
        1 <= nums.length <= 20
        0 <= nums[i] <= 1000
        0 <= sum(nums[i]) <= 1000
        -1000 <= target <= 1000

     */

    // KeyPoint 方法一 DFS 解法 (先序) => 比较容易想到
    // 1 <= nums.length <= 20 => 回溯必然能通过
    private int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    // 前序 dfs， 一般只有后序 dfs + 记忆化搜索
    // => 后序遍历，每个节点是有返回值，可以通过记忆化搜索避免重复计算
    // => 可以考虑使用：DFS (后序) + 记忆化搜索 优化
    private void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) ans++;
            return;
        }

        // 二叉树，两个分支，对应 '+' 和 '-'
        // => 区别：for 循环多叉树
        // => 想清楚什么时候使用：二叉树 和 多叉树
        dfs(nums, target, index + 1, sum + nums[index]);
        dfs(nums, target, index + 1, sum - nums[index]);
    }

    // KeyPoint 方法三 0-1 背包问题 (一维状态数组实现)
    public int findTargetSumWays2(int[] nums, int target) {

        // 转化 0-1 背包问题
        // 假设数组中所有数字的总和为 sum，若前面设置为负数的数字的总和是 neg，则设置为正数的数字的总和为 sum - neg
        // 根据 (sum - neg) - neg = target => neg = (sum - target) / 2
        // => 在数组 nums 列表中，不可重复的选择数字组合(每个数字选择一次)，使得组合中所有数字之和为 neg (背包容量)
        //    求有多少组合数()目标？
        // => 0-1 背包问题

        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;
        // 1.若 diff < 0 => sum < target，则不存在 neg
        // 2.若 diff 为奇数，则在数学运算中 diff / 2 = 小数，在整数中无法实现，则不存在 neg
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;

        // 0-1 背包问题
        // 在数组 nums 列表中，不可重复的选择数字组合，使得组合中所有数字之和为 neg
        // 求有多少组合数？

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
