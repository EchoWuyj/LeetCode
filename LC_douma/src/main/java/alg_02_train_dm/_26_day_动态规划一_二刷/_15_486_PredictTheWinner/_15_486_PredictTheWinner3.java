package alg_02_train_dm._26_day_动态规划一_二刷._15_486_PredictTheWinner;

/**
 * @Author Wuyj
 * @DateTime 2023-06-19 14:33
 * @Version 1.0
 */
public class _15_486_PredictTheWinner3 {

    // KeyPoint 方法三 动态规划
    public boolean PredictTheWinner(int[] nums) {

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

    // 优化：将 dp[i][i] 初始化赋值并入状态转移方程，减少一个 for 循环
    public boolean PredictTheWinner1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
