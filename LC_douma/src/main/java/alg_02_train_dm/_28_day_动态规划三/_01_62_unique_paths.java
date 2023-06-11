package alg_02_train_dm._28_day_动态规划三;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 13:45
 * @Version 1.0
 */
public class _01_62_unique_paths {
      /*
        62. 不同路径
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
        机器人每次只能向下或者向右移动一步。
        机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
        问总共有多少条不同的路径？

        提示：
        1 <= m, n <= 100
        题目数据保证答案小于等于 2 * 10^9

        KeyPoint 补充说明
        本题：和 LeetCode 64 最小路径和 同一类型算法题

        KeyPoint 超时判断
        O(n^3) => 200 ~ 500
        O(2^n) => 20 ~ 24
        => 根据 1 <= m, n <= 200，
        => 该 O(2^(m+n)) 必然超时，所以本题不能使用回溯来解决，将该方法排除

     */

    // KeyPoint 补充：直接 dfs 前序，没有使用记忆化搜索 => 超时
    private int res;

    public int uniquePaths(int m, int n) {
        dfs(m, n, 0, 0);
        return res;
    }

    public void dfs(int m, int n, int i, int j) {

        // 递归边界 => 机器人只能 向下 和 向右，并不会出现 i < 0 和 j < 0 情况
        if (i >= m || j >= n) {
            return;
        }

        if (i == m - 1 && j == n - 1) {
            // 返回值 void，无法将 res返回，故需要将 res 设置为全局变量
            res++;
        }

        dfs(m, n, i + 1, j);
        dfs(m, n, i, j + 1);
    }

    // KeyPoint 方法一 dfs + 记忆化搜索
    // 时间复杂度为 O(m×n)
    // => 对于每个网格单元，我们最多需要计算一次，然后将结果存储在记忆数组中，
    //    以供后续使用。因此，总共有m×n个子问题需要解决
    public int uniquePaths1(int m, int n) {
        int[][] memo = new int[m][n];

        // 二维数组初始化
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 不能跳过 for 循环，直接对 memo 初始化
        // Arrays.fill(memo, -1);

        return dfs(m, n, 0, 0, memo);
    }

    // 每个节点 => 位置索引(i,j)
    // 前序 dfs => 无法消除重复计算
    // 后序 dfs + 记忆化搜索 => 消除重复计算
    private int dfs(int m, int n, int i, int j, int[][] memo) {

        // 只有到达右下角 (m-1,n-1)，才算一条路径，返回 1
        if (i == m - 1 && j == n - 1) return 1;
        // 越界，返回 0
        if (i == m || j == n) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        // '向下' 或者 '向右' => 二叉树，两个分支
        int left = dfs(m, n, i, j + 1, memo);
        int right = dfs(m, n, i + 1, j, memo);
        memo[i][j] = left + right;

        return memo[i][j];
    }

    // KeyPoint 方法二 动态规划(左上到右下)
    public int uniquePaths2(int m, int n) {

        // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
        // 注意：dp[i][j] 根据后序 dfs 抽取出的，故 dp[i][j] 含义和后序 dfs 保持一致
        int[][] dp = new int[m][n];

        // 后序 dfs 先计算：右下角 dp[m-1][n-1]，再倒推往前计算
        // => 从而决定 for 循环遍历顺序
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    // 最后一行，最后一列，只能往右，往下走，故只有一条路径
                    // dp[m-1][n-1] =1 包括在内
                    dp[i][j] = 1;
                } else {
                    // if (i != m - 1 && j != n - 1) => 中间位置
                    // 根据 dfs 代码，抽取过来的
                    // 或者根据'填表法'来推理状态转移方程
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }

        // 补充：若一开始不是使用后序 dfs 抽取状态转移方程，还可以使用'填表法'来推理状态转移方程
        return dp[0][0];
    }

    // KeyPoint 方法三 动态规划(左上到右下) - 状态压缩
    public int uniquePaths3(int m, int n) {

        // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
        // 直接将 行坐标 i 去掉即可
        // 注意：直接将行坐标 i 去掉的条件
        // => dp[i][j] 只是依赖：下一行 dp[i + 1][j]，下一列 dp[i][j + 1]
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

    // dfs + 记忆化搜索 => 自己尝试写下，锻炼锻炼
    // KeyPoint 方法四 动态规划(右下到左上)
    public int uniquePaths4(int m, int n) {
        // dp[i][j]：表示从位置 [0, 0] 到 [i, j] 的路径数
        int[][] dp = new int[m][n];

        // 初始化：[0,0] 到 [0,0]，只有一条路径
        dp[0][0] = 1;

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // KeyPoint 方法五 动态规划(右下到左上) - 状态压缩
    public int uniquePaths5(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        int[] dp = new int[n];

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
