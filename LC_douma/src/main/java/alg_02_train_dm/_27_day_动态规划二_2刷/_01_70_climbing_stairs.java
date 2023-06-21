package alg_02_train_dm._27_day_动态规划二_2刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 15:51
 * @Version 1.0
 */
public class _01_70_climbing_stairs {
     /*
        70. 爬楼梯
        假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
        注意：给定 n 是一个正整数。

        示例 1：
        输入： 2
        输出： 2
        解释： 有两种方法可以爬到楼顶。
        1.  1 阶 + 1 阶
        2.  2 阶

        示例 2：
        输入： 3
        输出： 3
        解释： 有三种方法可以爬到楼顶。
        1.  1 阶 + 1 阶 + 1 阶
        2.  1 阶 + 2 阶
        3.  2 阶 + 1 阶

        提示：
        1 <= n <= 45

        KeyPoint 数据规模，dfs 超时
        1 <= n <= 45，使用递归(dfs)超时
        一般数据规模大于 20，使用 dfs 都是超时
     */

    // KeyPoint 方法一 递归 => 超时
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // KeyPoint 方法二 DFS + 记忆化搜索
    // 若不是使用记忆化搜索，同样超时
    public int climbStairs1(int n) {
        // 包括第 n 个楼梯，所以 memo 数组大小 n+1
        int[] memo = new int[n + 1];
        // 一定要初始化 memo
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    // dfs 后序遍历 => 方便转 dp
    // => 一开始父节点无法直接得到的值，则通过子节点的返回信息，再统计计算得到
    // => 返回值 int：表示该节点台阶数量对应的多少种走法
    private int dfs(int n, int[] memo) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (memo[n] != -1) return memo[n];

        // 回溯 DFS
        // 1.每次选择 1 或 2 个台阶，相当于 2 个分支，抽象成树形结构
        // 2.每个节点存储的是台阶数量，且树形结构存在重复节点，可以使用记忆化搜索

        // 左右子节点对应多少种走法
        // n-1，n-2 写法，从终点逆推的，选择走一步，还是选择走两步
        int left = dfs(n - 1, memo);
        int right = dfs(n - 2, memo);

        // 当前节点对应多少种走法 = 左 + 右
        memo[n] = left + right;
        return memo[n];
    }

    // KeyPoint 方法三 动态规划
    public int climbStairs2(int n) {

        // 保证 n > 2，否则，若 n = 2，则 dp[2] 存在越界
        // 即：当测试用例 n = 1，执行 for 循环中的状态转移方程，dp[i - 2] 索引越界
        // 此时，力扣报的错误不是解答错误，而是执行错误，如下：
        // java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2

        if (n == 1) return 1;
        if (n == 2) return 2;

        // 1. 状态 dp[i]：表示走到第 i 个台阶的方法数
        int[] dp = new int[n + 1];

        // 2. 状态初始化
        dp[1] = 1;
        dp[2] = 2;

        // 3. 状态转移
        for (int i = 3; i <= n; i++) {
            // i 个台阶的方法数 = i-1 个台阶的方法数 + i-2 个台阶的方法数
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回结果
        return dp[n];
    }

    // KeyPoint 方法四 动态规划 + 状态空间压缩
    //
    public int climbStairs3(int n) {
        // bug 修复：需要对 1 和 2 做特判，要不然当 n = 1 的时候，会返回错误结果
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 使用 prev 代替 dp[i-2] => 和 i 考的远使用 prev
        // 使用 curr 代替 dp[i-1] => 和 i 考的近使用 curr

        int prev = 1;
        int curr = 2;

        for (int i = 3; i <= n; i++) {
            int tmp = curr + prev;
            // 注意： 赋值先后顺序
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
