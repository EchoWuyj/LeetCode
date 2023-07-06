package alg_02_train_dm._28_day_动态规划三_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 13:45
 * @Version 1.0
 */
public class _01_62_unique_paths1 {
      /*
        62. 不同路径
        一个机器人位于一个 m x n 网格的左上角 (起始点在下图中标记为 "Start" )。
        机器人每次只能向下或者向右移动一步。
        机器人试图达到网格的右下角 (在下图中标记为 "Finish" )。
        问总共有多少条不同的路径？

        提示：
        1 <= m, n <= 100
        题目数据保证答案小于等于 2 * 10^9

        KeyPoint 补充说明
        本题：和 LeetCode 64 最小路径和 同一类型算法题

        KeyPoint 超时判断
        O(n^3) => 200 ~ 500
        O(2^n) => 20 ~ 24 (48 都是 dfs 超时)
        => 根据 1 <= m, n <= 200，
        => 该 O(2^(m+n)) 必然超时，所以本题不能使用回溯来解决，将该方法排除

     */

    // KeyPoint 方法一 dfs 前序，没有使用记忆化搜索 => 超时
    private int res;

    public int uniquePaths(int m, int n) {
        // 主函数得调用 dfs，否则 res = 0
        dfs(m, n, 0, 0);
        return res;
    }

    public void dfs(int m, int n, int i, int j) {

        // 递归边界 => 机器人只能 向下 和 向右，并不会出现 i < 0 和 j < 0 情况
        if (i >= m || j >= n) {
            return;
        }
        // 先是外侧边界，再是内侧边界
        if (i == m - 1 && j == n - 1) {
            // 返回值 void，无法将 res返回，故需要将 res 设置为全局变量
            res++;
        }

        dfs(m, n, i + 1, j);
        dfs(m, n, i, j + 1);
    }

    // KeyPoint 方法二 dfs + 记忆化搜索
    // 时间复杂度为 O(m*n)
    // => 对于每个网格单元，我们最多需要计算一次，然后将结果存储在记忆数组中，
    //    以供后续使用，因此，总共有 m*n 个子问题需要解决
    public int uniquePaths1(int m, int n) {

        // 每个网格中位置 (i,j) 都是一个状态，故使用二维 dp 数组来记录
        int[][] memo = new int[m][n];

        // 二维数组初始化
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 不能跳过 for 循环，直接对 memo 初始化，memo 是二维数组
        // Arrays.fill(memo, -1);

        // KeyPoint 加深对后序遍历的理解
        // 后序遍历，可以理解成从叶子到 root，每一分支都累加路径，最后 root 返回值即为路径总和
        return dfs(m, n, 0, 0, memo);
        // 注意，不是返回 memo[m-1][n-1]，记忆化搜索 memo 只是作为缓存来使用的，不是 dp 数组
    }

    // 每个节点 => 位置索引(i,j)
    // 前序 dfs => 无法消除重复计算
    // 后序 dfs + 记忆化搜索 => 消除重复计算
    private int dfs(int m, int n, int i, int j, int[][] memo) {

        // 越界，返回 0
        if (i == m || j == n) return 0;
        // 只有到达右下角 (m-1,n-1)，才算一条路径，返回 1
        if (i == m - 1 && j == n - 1) return 1;

        if (memo[i][j] != -1) return memo[i][j];

        // '向下' 或者 '向右' => 二叉树，两个分支
        int left = dfs(m, n, i, j + 1, memo);
        int right = dfs(m, n, i + 1, j, memo);
        memo[i][j] = left + right;
        return memo[i][j];
    }
}
