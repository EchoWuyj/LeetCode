package alg_02_train_dm._21_day_综合应用二_二刷;

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

    // 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
    // => 抽象成多叉树，2,3,5 相当于 每个分支整除，使用 DFS 来求解

    // KeyPoint 方法一 DFS
    public boolean isUgly1(int n) {
        // 特判：0 不是丑数
        if (n == 0) return false;
        return dfs(n);
    }

    // 判断整数 n 是否可以被 2 或 3 或 5 整除
    private boolean dfs(int n) {
        // 递归边界 => 1 是丑数
        if (n == 1) return true;
        int[] nums = {2, 3, 5};
        // 如：n = 9，使用 2、3、5 依次判断是否能被整除
        for (int num : nums) {
            // 抽象树形结构，当层包含 num，递归判断下一层
            // 注意：这里 n % num，而不是 n / num
            if (n % num == 0) {
                if (dfs(n / num)) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 迭代
    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] nums = {2, 3, 5};
        for (int num : nums) {
            // 使用 while 循环，对当前的 num 多次循环判断
            // 直到其不满足 n % num != 0，而不是使用 if 单次判断
            while (n % num == 0) n /= num;
        }
        return n == 1;
    }
}
