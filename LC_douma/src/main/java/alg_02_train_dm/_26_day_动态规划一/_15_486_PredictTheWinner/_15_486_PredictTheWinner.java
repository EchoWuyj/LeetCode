package alg_02_train_dm._26_day_动态规划一._15_486_PredictTheWinner;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 21:22
 * @Version 1.0
 */
public class _15_486_PredictTheWinner {
    /*
        486. 预测赢家
        给你一个整数数组 nums。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
        玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0

        每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），
        取到的数字将会从数组中移除（数组长度减 1）。玩家选中的数字将会加到他的得分上。
        当数组中没有剩余数字可取时，游戏结束。

        如果玩家 1 能成为赢家，返回 true 。
        如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。
        你可以假设每个玩家的玩法都会使他的分数最大化。

        示例 1：
        输入：nums = [1,5,2]
        输出：false
        解释：一开始，玩家 1 可以从 1 和 2 中进行选择。
        如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。
        如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
        所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
        因此，玩家 1 永远不会成为赢家，返回 false 。

        示例 2：
        输入：nums = [1,5,233,7]
        输出：true
        解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。
        无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
        最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。

        提示：
        1 <= nums.length <= 20
        0 <= nums[i] <= 10^7

        KeyPoint 注意：
         1 <= nums.length <= 20
         => 根据数据规模，dfs 回溯也是能通过的

     */

    // KeyPoint 方法一 DFS
    // 本题：通过穷举求解，列举每次选择的可能，即列举所有可能分支，转成树形结构来做
    // => 按照数组索引 [i,j] 划分选择
    // => 优化：树形结构存在重复节点 [i,j]，即存在重复计算，通过记忆化搜索消除
    public boolean PredictTheWinner1(int[] nums) {
        // dfs 结果 >= 0，则 玩家 1 获胜
        return dfs(nums, 0, nums.length - 1) >= 0;
    }

    // 返回值：当前节点比对方，最多多的分数
    private int dfs(int[] nums, int i, int j) {
        // 最后只剩一个元素
        if (i == j) return nums[i];

        // 递归中，分析某一层的处理逻辑
        // 1.若拿 nums[i]，下一层子节点 dfs，dfs(nums, i + 1, j)
        // pickI：计算当前节点比对方(子节点)多的分数
        int pickI = nums[i] - dfs(nums, i + 1, j);

        // 2.若拿 nums[j]，下一层子节点 dfs，dfs(nums, i, j - 1)
        // pickJ：计算当前节点比对方(子节点)多的分数
        int pickJ = nums[j] - dfs(nums, i, j - 1);

        // 比较，取最多的分数
        return Math.max(pickI, pickJ);
    }

    // KeyPoint 方法二 DFS + 记忆化搜索
    public boolean PredictTheWinner2(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];

        // 初始化缓存
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        return dfs(nums, 0, nums.length - 1, memo) >= 0;
    }

    // 玩家 1 在区间 [i, j] 内可以赢的最多的分
    // 虽然记忆化搜索避免了重复计算，但是记忆化搜索是通过递归实现，递归存在开销
    // 若想要消除递归开销，只能使用动态规划
    private int dfs(int[] nums, int i, int j, int[][] memo) {
        if (i == j) return nums[i];
        // 查缓存
        if (memo[i][j] != Integer.MIN_VALUE) return memo[i][j];

        // 记忆化搜索，记得传入缓存 memo
        int pickI = nums[i] - dfs(nums, i + 1, j, memo);
        int pickJ = nums[j] - dfs(nums, i, j - 1, memo);

        // 存缓存
        memo[i][j] = Math.max(pickI, pickJ);
        return memo[i][j];
    }

    // KeyPoint 方法三 动态规划
    public boolean PredictTheWinner3(int[] nums) {
        // dp[i][j] 表示玩家 1 在区间 [i, j] 内，玩家可以赢的最多的分
        // 状态参数：区间开始索引，结束索引 [start, end]
        // 状态值：区间 [i, j] 内，玩家可以赢的最多的分
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // 区间 [i,j]，则必然有 i < j
        // dp[i][j] 依赖关系 => 遍历方向 => 从下往上，从左往右
        // 时间复杂度 O(n^2)
        for (int i = n - 2; i >= 0; i--) {
            // 注意：j 是从左往右遍历，不能是从右往左遍历
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}
