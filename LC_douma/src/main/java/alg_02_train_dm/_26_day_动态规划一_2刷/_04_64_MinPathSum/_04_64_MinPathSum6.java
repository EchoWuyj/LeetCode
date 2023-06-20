package alg_02_train_dm._26_day_动态规划一_2刷._04_64_MinPathSum;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 19:04
 * @Version 1.0
 */
public class _04_64_MinPathSum6 {
    public int minPathSum(int[][] grid) {

        // 优化 => 状态压缩
//        // KeyPoint 1.第一行
//        if (i == 0 && j != 0) {
//            dp[i][j] = grid[i][j] + dp[i][j - 1];
//            // KeyPoint 2.第一列
//        } else if (i != 0 && j == 0) {
//            dp[i][j] = grid[i][j] + dp[i - 1][j];
//            // KeyPoint 3.中间位置
//        } else if (i != 0 && j != 0) {
//            dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
//        }

        // '上一行状态值' => dp[i - 1][j]
        // '前一个状态值' => dp[i][j - 1]

        // 从上面代码看出，当前状态只和 '上一行状态值' dp[i - 1][j] 和 '前一个状态值' dp[i][j - 1]
        //  =>当前状态只和上一行和本行有关 =>压缩状态

        // 坐标相对位置
        //            (i-1,j)
        //  (i,j-1)   (i, j)
        // 代码修改技巧，只要在原有的二维数组代码中，将行 [i] 去掉即可

        // 具体案例，作为师范

        // grid 矩阵
        // 1 3 1 1
        // 1 5 1 2       dp 0 0 0 0
        // 4 2 1 6

        // 1 3 1 1
        // ↑
        // 1 5 1 2       dp 1 0 0 0
        // 4 2 1 6

        // 1 3 1 1
        //   ↑
        // 1 5 1 2       dp 1 4 0 0
        // 4 2 1 6            ↑
        //                  dp[j] = grid[i][j] + dp[j - 1];
        //                  dp[1] = grid[0][1](3) + dp[0](1) = 4

        // 1 3 1 1
        // 1 5 1 2
        // i             dp 2 4 5 6
        // 4 2 1 6          ↑
        //                dp[j] = grid[i][j] + dp[j];
        //                dp[0] = grid[0][0](1) + dp[0](1) = 2

        // 1 3 1 1
        // 1 5 1 2
        //   i           dp 2 7 5 6
        // 4 2 1 6            ↑
        //                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1])
        //                dp[1] = grid[1][1](5) + Math.min(dp[1](4), dp[0](2)) = 7

        // 行
        int m = grid.length;
        // 列
        int n = grid[0].length;

        // 状态定义：dp 数组表示：从 (0,0) 到 二维数组某行 i 最短路径值
        int[] dp = new int[n];

        // 状态初始化
        dp[0] = grid[0][0];

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 第一行
                if (i == 0 && j != 0) {
                    // 代码修改：将二维数组中的 i 都去掉，其他的位置不用修改
                    dp[j] = grid[i][j] + dp[j - 1];
                    // 第一列
                } else if (i != 0 && j == 0) {
                    dp[j] = grid[i][j] + dp[j];
                    // 中间位置
                } else if (i != 0 && j != 0) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }
        // 返回结果
        return dp[n - 1];
    }
}
