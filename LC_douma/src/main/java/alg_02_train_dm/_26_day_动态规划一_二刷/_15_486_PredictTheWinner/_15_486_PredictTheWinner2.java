package alg_02_train_dm._26_day_动态规划一_二刷._15_486_PredictTheWinner;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-19 14:33
 * @Version 1.0
 */
public class _15_486_PredictTheWinner2 {

    // KeyPoint 方法二 DFS + 记忆化搜索
    public boolean PredictTheWinner(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];

        // 初始化缓存
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(nums, 0, nums.length - 1, memo) >= 0;
    }

    // 玩家 1 在区间 [i, j] 内可以赢的最多的分
    // 虽然记忆化搜索避免了重复计算，但是记忆化搜索是通过递归实现，递归存在开销
    // 若想要消除递归开销，只能使用动态规划
    private int dfs(int[] nums, int i, int j, int[][] memo) {
        if (i == j) return nums[i];
        // 查缓存
        if (memo[i][j] != -1) return memo[i][j];

        // 记忆化搜索，记得传入缓存 memo
        int pickI = nums[i] - dfs(nums, i + 1, j, memo);
        int pickJ = nums[j] - dfs(nums, i, j - 1, memo);

        // 存缓存
        memo[i][j] = Math.max(pickI, pickJ);
        return memo[i][j];
    }
}
