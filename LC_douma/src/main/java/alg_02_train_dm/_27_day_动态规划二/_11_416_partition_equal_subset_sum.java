package alg_02_train_dm._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 13:31
 * @Version 1.0
 */
public class _11_416_partition_equal_subset_sum {
     /*
        416. 分割 等和 子集
        给你一个 只包含正整数 的 非空 数组 nums 。
        请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
    
        示例 1：
        输入：nums = [1,5,11,5]
        输出：true
        解释：数组可以分割成 [1, 5, 5] 和 [11] 。
    
        示例 2：
        输入：nums = [1,2,3,5]
        输出：false
        解释：数组不能分割成两个元素和相等的子集。
        
        提示：
        1 <= nums.length <= 200
        1 <= nums[i] <= 100

     */

    // 将该问题转变为 0-1 背包问题
    // 先计算得到数组的总和为 sum，然后将 sum / 2 得到一半，则为子集和，记为 target (背包容量)
    // 关键：在数组中，能否找到一个子集和为 target ?
    // 因为：子集 1 + 子集 2 = target = sum / 2，子集1 = target，则：子集2 = sum - target(sum / 2) = target
    // => 在数组 nums 中，不可重复的选择数字组合，是否存在和等于 target 的组合呢？
    // => 本题是判断是否存在 (不是最大值，最小值，组合数)，故状态数组中存储 boolean
    // => 状态定义尽量保持和题目目标一致，避免出现未知的错误
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 奇数 / 2，存在小数，必然不存在，直接返回 false
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        // dp[c]：表示从 nums 中是否可以找到总和等于 c 的元素组合
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                // || 或的关系，只要有一种方案为 true，整体则为 true
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }

    // KeyPoint 存在 bug => 简单了解即可
    public boolean canPartition1(int[] nums) {

        // 补充说明：数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合
        // 类比'目标和'题目
        // 状态定义：dp[c] 表示从 nums 中找到总和等于 c 的元素组合数
        // 状态转移方程：dp[c] = dp[c] + dp[c - nums[i]]
        // 最后只要判断 dp[target] 是否大于 0 即可

        // => 以上实现，逻辑上是没有任何问题
        // => 但是，等于 target 的元素组合数 (也就是状态值) 可能会非常的大，以至于溢出变成负数
        // 测试用例报错：[100,100,100,100,100,100,...,100,100,100]
        // 详情请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4TGJ2

        // 题目中没有说明：数据保证答案符合 32 位整数范围
        // => 数据是有效的，不会越界
        // => dp[j] 状态值不会数据溢出，从而保证状态转移方程不会出现错误

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        // dp[c]：表示从 nums 中找到总和等于 c 的元素组合数
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int c = target; c >= nums[i]; c--) {
                dp[c] = dp[c] + dp[c - nums[i]];
            }
        }

        // 判断从 nums 中找到总和等于 c 的元素组合数是否大于 0
        // 如果是大于 0，那么说明存在，否则不存在
        return dp[target] > 0;
    }

    // KeyPoint 尝试修改数据类型，解决数据溢出问题 => 测试用例没有完全通过
    public boolean canPartition2(int[] nums) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        // dp[c]：表示从 nums 中找到总和等于 c 的元素组合数
        long[] dp = new long[target + 1];

        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int c = target; c >= nums[i]; c--) {
                dp[c] = dp[c] + dp[c - nums[i]];
            }
        }
        // 判断从 nums 中找到总和等于 c 的元素组合数是否大于 0
        // 如果是大于 0，那么说明存在，否则不存在
        return dp[target] > 0;
    }
}
