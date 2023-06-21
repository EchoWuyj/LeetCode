package alg_02_train_dm._28_day_动态规划三_2刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-20 21:26
 * @Version 1.0
 */
public class _01_62_unique_paths2 {

    // KeyPoint 方法三 动态规划(左上到右下)
    public int uniquePaths1(int m, int n) {

        // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
        // 注意：dp[i][j] 根据后序 dfs 抽取出的，故 dp[i][j] 含义和后序 dfs 保持一致
        int[][] dp = new int[m][n];

        // 后序 dfs 先计算：右下角 dp[m-1][n-1]，再倒推往前计算
        // => 从而决定 for 循环遍历顺序
        // => (i,j) 是网格中位置索引，没有 i < j 的要求，dp 不用划分右斜下对角线
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    // 最后一行，最后一列，只能往右，往下走，故只有一条路径
                    // dp[m-1][n-1] =1 包括在内
                    dp[i][j] = 1;
                } else {
                    // if (i != m - 1 && j != n - 1) => 中间位置
                    // 根据 dfs 代码，抽取过来的，或者根据'填表法'来推理状态转移方程
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                    // 关于状态转移方程中是 dp[i+1][j] 还是 dp[i-1][j]，通过坐标位置确定
                    // 若 dp[i][j] 是由：后面位置状态 dp[i+1][j] 推导，则状态转移方程中选择 dp[i+1][j]
                }
            }
        }

        // 补充：若一开始不是使用后序 dfs 抽取状态转移方程，还可以使用'填表法'来推理状态转移方程
        // 通过，dp 初始化的第一个位置，如：dp[m-1][n-1] = 1，推导最后返回的位置 dp[0][0]
        return dp[0][0];
    }

    // 另外一种 dp 方式
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            // j >= 0，不是 j >= n，for 循环执行，导致没有赋值成功
            // 如果 dp 最终返回为默认值，则说明 dp 没有赋值成功，检查是否是 for 循环问题
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];

        // 类比 04_64_MinPathSum4  if 判断分支
//        for (int i = m - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                if (i == m - 1 && j != n - 1) {
//                    dp[i][j] = dp[i][j + 1] + grid[i][j];
//                } else if (i != m - 1 && j == n - 1) {
//                    dp[i][j] = dp[i + 1][j] + grid[i][j];
//                } else if (i != m - 1 && j != n - 1) {
//                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
//                }
//            }
//        }
    }

    // KeyPoint 优化 状态压缩
    public int uniquePaths3(int m, int n) {

        // 坐标位置关系
        //                   (i,j)      (i,j+1)
        //                   (i+1,j)

        // 一维 dp数组： ... dp[i+1][j]  dp[i][j+1] ...
        // 通过 dp[i+1][j] 和 dp[i][j+1]，计算出 dp[i][j]，再去将 dp[i+1][j] 覆盖

        // => dp[i][j] 只是依赖：下一行 dp[i + 1][j]，后一列 dp[i][j + 1]
        // => 直接将 行坐标 i 去掉即可
        // 注意：直接将行坐标 i 去掉的条件，只有这种情况下，才能直接将 i 去掉

        int[] dp = new int[n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    // 直接将 行坐标 i 去掉即可
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j + 1] + dp[j];
                }
            }
        }
        return dp[0];
    }
}
