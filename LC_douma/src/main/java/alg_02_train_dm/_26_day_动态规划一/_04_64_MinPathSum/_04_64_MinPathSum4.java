package alg_02_train_dm._26_day_动态规划一._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:03
 * @Version 1.0
 */
public class _04_64_MinPathSum4 {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 状态定义：dp[i][j] 表示从坐标 (i,j) 到右下角(终点)的最小路径和
        int[][] dp = new int[m][n];

        // 状态初始化
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 状态转移 => 通过填表，来获取状态转移方程
        // 从 dp[右下角] -> dp[0][0] => 已知状态 -> 未知状态
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 1.最后一行
                if (i == m - 1 && j != n - 1) {
                    // 如：dp[m-1][n-2] = dp[m-1][n-1] + grid[m-1][n-2]
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                    // 2.最后一列
                } else if (i != m - 1 && j == n - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                    // 3.中间位置
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
                }
            }
        }

        // 返回结果
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new _04_64_MinPathSum4().minPathSum(grid));
    }
}
