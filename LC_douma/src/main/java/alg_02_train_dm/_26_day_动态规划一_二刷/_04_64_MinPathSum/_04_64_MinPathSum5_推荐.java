package alg_02_train_dm._26_day_动态规划一_二刷._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:04
 * @Version 1.0
 */
public class _04_64_MinPathSum5_推荐 {
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        // 重新定义状态
        // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
        int[][] dp = new int[m][n];

        // 状态初始化
        dp[0][0] = grid[0][0];

        // 状态转移：已知状态 => 推导未知状态
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 1.第一行
                if (i == 0 && j != 0) {
                    // i == 0，为了保证同一行，i 保持不变
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                    // 2.第一列
                } else if (i != 0 && j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                    // KeyPoint 3.中间位置 => 一定得明确写出来，否则可能是 (0,0) 位置
                } else if (i != 0 && j != 0) {
                    // '上一个状态值' 或者 '前一个状态值'
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // 返回结果
        return dp[m - 1][n - 1];
    }
}
