package alg_02_train_dm._27_day_动态规划二_2刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 16:09
 * @Version 1.0
 */
public class _02_746_min_cost_climbing_stairs {

     /*
        746. 使用最小花费爬楼梯
        数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

        每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，
        你就可以选择向上：爬一个阶梯 或者 爬两个阶梯。

        请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。

        示例 1：
        输入：cost = [10, 15, 20] => 注意：楼顶不是 20，而是 20 后面一个位置
        输出：15
        解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
             注意：每次还是可以选择：爬一个阶梯 或者 爬两个阶梯

        示例 2：
        输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
        输出：6
        解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。

        提示：
        cost 的长度范围是 [2, 1000]。
        cost[i] 将会是一个整型数据，范围为 [0, 999]

     */

    // KeyPoint 方法一  dfs + 记忆化搜索 => 自己想出来的！
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(cost, n, memo);
    }

    private int dfs(int[] cost, int n, int[] memo) {
        if (n == 0 || n == 1) return 0;
        if (memo[n] != Integer.MAX_VALUE) return memo[n];

        // KeyPoint 调试 bug，通过最简单的例子，来模拟程序执行，从而找到问题所在
        int left = cost[n - 1] + dfs(cost, n - 1, memo);
        int right = cost[n - 2] + dfs(cost, n - 2, memo);
        memo[n] = Math.min(left, right);

        return memo[n];
    }

    // KeyPoint 方法一 dfs + 记忆化搜索
    // 时间复杂度 O(n)
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        // 包含索引 n 位置，表示楼顶
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs1(cost, n, memo);
    }

    // dfs 后序遍历，返回值 int：到达该节点(台阶)花费最小的体力值
    private int dfs1(int[] cost, int i, int[] memo) {
        // 注意：最开始到达 0 和 1 位置体力消耗为 0
        // 但是从 0 或者 1 往上走，是需要自身消耗体力值 cost[0] 或 cost[1]
        if (i == 0 || i == 1) return 0;
        if (memo[i] != -1) return memo[i];

        // 左子树，爬一个阶梯
        int left = dfs1(cost, i - 1, memo);
        // 右子树，爬两个阶梯
        int right = dfs1(cost, i - 2, memo);

        // left 左子树花费最小的体力值 + cost[i - 1] 当前节点花费的体力值
        // 如：最开始到达 0 和 1 位置体力消耗为 0
        // 但是从 0 或者 1 往上走，是需要自身消耗体力值 cost[0] 或 cost[1]
        memo[i] = Math.min(left + cost[i - 1], right + cost[i - 2]);
        return memo[i];
    }

    // KeyPoint 方法二 动态规划
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        // 状态 dp[i]：表示走到第 i 个台阶使用的最小花费
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            // for 循环里面，基本都是关于：循环变量 i，而不是固定值 n
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    // KeyPoint 方法三 动态规划 + 状态空间压缩
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        //  prev     curr     tmp
        // dp[i-2]  dp[i-1]  dp[i]
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            // 不是每题都是 tmp = prev + curr
            // 每道题的状态转移方程都是不同的
            int tmp = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
