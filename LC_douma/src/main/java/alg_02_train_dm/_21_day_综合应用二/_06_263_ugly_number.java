package alg_02_train_dm._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 16:24
 * @Version 1.0
 */
public class _06_263_ugly_number {
       /*
            263. 丑数
            给你一个整数 n ，请你判断 n 是否为 丑数 。
            如果是，返回 true ；否则，返回 false 。

            丑数 就是只包含质因数 2、3 和/或 5 的正整数。

            示例 1：
            输入：n = 6
            输出：true
            解释：6 = 2 × 3

            示例 2：
            输入：n = 8
            输出：true
            解释：8 = 2 × 2 × 2

            示例 3：
            输入：n = 14
            输出：false
            解释：14 不是丑数，因为它包含了另外一个质因数 7 。

            示例 4：
            输入：n = 1
            输出：true
            解释：1 通常被视为丑数。

            提示：
            -2^31 <= n <= 2^31 - 1
     */

    // 抽象成多叉树，每个分支 2,3,5 整除，使用 DFS 来求解
    // KeyPoint 方法一 DFS
    public boolean isUgly1(int n) {
        // 0 不是丑数
        if (n == 0) return false;
        return dfs(n);
    }

    // 判断整数 n 是否可以被 2、3、5 整除
    private boolean dfs(int n) {
        // 1 是丑数
        if (n == 1) return true;
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            if (n % factor == 0) {
                if (dfs(n / factor)) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 迭代
    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) n /= factor;
        }
        return n == 1;
    }
}
