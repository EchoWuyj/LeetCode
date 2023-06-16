package alg_02_train_dm._28_day_动态规划三;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 15:32
 * @Version 1.0
 */
public class _03_120_triangle {
    
     /* 
         120. 三角形最小路径和
         给定一个三角形 triangle，找出自顶向下的最小路径和。
         每一步只能移动到下一行中相邻的结点上。
         相邻的结点在这里指的是下标与上一层结点下标相同或者等于上一层结点下标 + 1 的两个结点。
         也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
    
         输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
         输出：11
         解释：如下面简图所示
             2          2
            3 4         3 4
           6 5 7   =>   6 5 7
          4 1 8 3       4 1 8 3
         自顶向下的最小路径和为  11（即 2 + 3 + 5 + 1 = 11）

         提示：
         1 <= triangle.length <= 200
         triangle[0].length == 1
         triangle[i].length == triangle[i - 1].length + 1
         -104 <= triangle[i][j] <= 10^4

         进阶：
         你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
     */

    // KeyPoint 补充：前序 dfs
    private int minPath = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        dfs(triangle, 0, 0, triangle.get(0).get(0));
        return minPath;
    }

    public void dfs(List<List<Integer>> triangle, int i, int j, int path) {
        // 节点移动，先向下，再向右
        // 当 i == triangle.size() 是先越界，直接可以 return 0
        // 不需要再判断 j，其实三角形每层 j 范围都不一样，不好判断
        if (i == triangle.size()) {
            minPath = Math.min(minPath, path);
            // 返回值 void，不要忘了 return
            return;
        }

        // 调用 dfs，i+1 会越界，故需要加一层判断
        dfs(triangle, i + 1, j, path + (i + 1 != triangle.size() ? triangle.get(i + 1).get(j) : 0));
        dfs(triangle, i + 1, j + 1, path + (i + 1 != triangle.size() ? triangle.get(i + 1).get(j + 1) : 0));
    }

    // KeyPoint 方法一 dfs + 记忆化搜索
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        // KeyPoint 后序遍历，可以理解成从叶子到 root，每一分支都选择最小路径，最后 root 返回值即为最小路径和
        return dfs(triangle, 0, 0, memo);
        // 注意，不是返回 memo[n-1][n-1]，记忆化搜索 memo 只是作为缓存来使用的，不是 dp 数组
    }

    // 节点 (i,j) 到叶子节点的最短路径和
    private int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        // 节点移动，先向下，再向右
        // 当 i == triangle.size() 是先越界，直接可以 return 0
        // 不需要再判断 j，其实三角形每层 j 范围都不一样，不好判断
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];

        // 下一步可以移动到下一行的下标 i 或 i + 1  => 二叉树
        // 从 root 到叶子节点都是一条路径 => 求解最小路径和
        int left = dfs(triangle, i + 1, j, memo);
        int right = dfs(triangle, i + 1, j + 1, memo);

        // 若左右子节点越界，则 left = 0，right = 0，Ma
        // => Math.min(left, right) 选择最小值
        // => 加上自身路径值 triangle.get(i).get(j)
        // => 节点 (i,j) 到叶子节点的最短路径和
        memo[i][j] = Math.min(left, right) + triangle.get(i).get(j);
        return memo[i][j];
    }

    // KeyPoint 方法二 动态规划
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 [i, j] 到底边(最后一行)的最小路径和
        int[][] dp = new int[n][n];

        // 初始化最后一行
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }

        // 注意：i 是从倒数第二行开始
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1])
                        + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    // KeyPoint 方法三 动态规划 + 状态空间压缩
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 [i, j] 到底边(最后一行)的最小路径和。
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 能不能将行坐标省略 => 关键在于计算完状态后面已经不会再使用，故可以将其覆盖
                dp[j] = Math.min(dp[j], dp[j + 1])
                        + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
