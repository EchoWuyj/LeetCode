package alg_02_train_dm._28_day_动态规划三_2刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 13:48
 * @Version 1.0
 */
public class _02_63_unique_paths_ii {
        /*
            63. 不同路径 II
            一个机器人位于一个 m x n网格的左上角 (起始点在下图中标记为 "Start" )。
            机器人每次只能向下或者向右移动一步。
            机器人试图达到网格的右下角(在下图中标记为 "Finish" )。
            现在考虑网格中有障碍物，问总共有多少条不同的路径？
            网格中的障碍物和空位置分别用 1 和 0 来表示。

            提示：
            1 <= m, n <= 100
            每个格子中只能为 0 或 1

            KeyPoint 补充说明：何时使用回溯 ? 何时使用动态规划 ?
            首先看取值范围：1 <= m, n <= 100，每次移动时，我们都有两个选择
            向下或向右，而在 m+n 步内，一共做出 m+n 次选择，因此，总的可能路径数是 2^(m+n) => 必然超时

            O(n^3) => 200 ~ 500
            O(2^n) => 20 ~ 24

            1.若是求走迷宫的'路径'，即根到叶子，必然是回溯；
            2.若是走迷宫的'路径的条数'，必然是dp

     */

    // 动态规划
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        int[][] dp = new int[m][n];

        // 障碍物 => 1 => 1 形状像一道墙
        // 空位置 => 0

        // 2. 状态初始化
        // 2.1. 左上角元素
        // 左上角有可能是障碍物，故是需要单独判断的，
        // dp 数组，默认初始化是 0，对非 0 情况进行特判
        // => 若 (0,0) 不为障碍物，则 dp[0][0] = 1
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        }

        // 2.2：初始化第一列，所有点到终点的路径数
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) {
                // 不设置 dp[i][0] = 1，使用默认的 dp[i][0] = 0，表示没有路径能到达
                dp[i][0] = 1;
            }
        }

        // 2.3：初始化第一行，所有点到终点的路径数
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0 && dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            }
        }

        // 3. 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 障碍物，将 dp[i][j] 设置 0
                if (obstacleGrid[i][j] == 1) {
                    // dp[i][j] 可以不用赋值为 0，因为 dp[i][j] 默认值就是 0
                    dp[i][j] = 0;
                    // dp[i][j] 已经被赋值了，加上 continue，跳过后续代码，避免 dp[i][j] 被重复赋值
                    // KeyPoint continue 必须得加，否则报错
                    continue;
                }
                // 即使 dp[i][j - 1] 或者 dp[i - 1][j] = 0，也是可以两者相加为 dp[i][j]
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    // 动态规划 => 统一初始化
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        int[][] dp = new int[m][n];

        // 2. 状态初始化
        // 状态初始化合并到状态转移方程中，方便后续的状态压缩

        // 3. 状态转移方程
        // 修改 i 和 j 其实位置，分别从 (0,0) 开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // 上面 if 和 下面 if 是平行独立的，每个位置都需要进行两次 if 判断
                // 经过 上面 if 判断之后，再执行 下面 if 判断，则 obstacleGrid[i][j] 必为 0
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    // dp[i][j] 已经被赋值了，加上 continue，跳过后续代码，避免 dp[i][j] 被重复赋值
                    // KeyPoint continue 必须得加，否则报错
                    continue;
                }

                // 合并 dp[i][j] 状态初始化
                // 根据不同的位置，进行不同的 dp[i][j] 赋值操作，通过 if else 判断实现
                // 关键是注意数组索引越界问题，如：dp[i - 1][j]，dp[i][j - 1]

                // 左上角
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    // 第一列
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                    // 第一行
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                    // 一般位置 (i,j)
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // 动态规划 + 状态压缩
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];

        // 坐标位置关系
        //         (i-1,j)
        // (i,j-1) (i,j)

        // => dp[i][j] 只是依赖：上一行 dp[i - 1][j] ，前一列 dp[i][j - 1]
        // => 直接将 行坐标 i 去掉即可
        // 注意：直接将行坐标 i 去掉的条件，只有这种情况下，才能直接将 i 去掉

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                } else if (j == 0) {
                    dp[j] = dp[j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[n - 1];
    }
}
