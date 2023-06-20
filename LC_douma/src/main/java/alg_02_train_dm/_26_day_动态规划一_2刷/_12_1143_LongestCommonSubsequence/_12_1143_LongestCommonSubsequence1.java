package alg_02_train_dm._26_day_动态规划一_2刷._12_1143_LongestCommonSubsequence;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 19:46
 * @Version 1.0
 */
public class _12_1143_LongestCommonSubsequence1 {

    /*
        1143. 最长 公共 子序列 (LCS)
        给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。
        如果不存在 公共子序列 ，返回 0 。
        两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

        一个字符串的 子序列 是指这样一个新的字符串：
        它是由原字符串在不改变字符的相对顺序的情况下删除某些字符，(也可以不删除任何字符)后组成的新字符串。
        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。

        示例 1：
        输入：text1 = "abcde", text2 = "ace"
        输出：3
        解释：最长公共子序列是 "ace" ，它的长度为 3 。

        示例 2：
        输入：text1 = "abc", text2 = "abc"
        输出：3
        解释：最长公共子序列是 "abc" ，它的长度为 3 。

        提示：
        1 <= text1.length, text2.length <= 1000
        text1 和 text2 仅由小写英文字符组成。
     */

    // KeyPoint 方法一 动态规划
    // 首先是最优值问题 + 最优值问题通过穷举方式得到 + 穷举过程存在重复计算 => 动态规划
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 对于两个字符串的动态规划问题 => 对应二维 dp 数组，i,j 分别对应一个字符串
        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        // KeyPoint 从 i 和 j 都是空串 "" 开始的，故 dp 中 m 和 n 需要 +1
        int[][] dp = new int[m + 1][n + 1];

        // 默认初始化

        // 第一行为 0
        // dp[0][j] = 0;

        // 第一列为 0
        // dp[i][0] = 0;

        // s： a b c d e
        //       i
        // p： a c e
        //       j

        // 状态转移方程
        // 1.若 s.charAt(i-1) == p.char(j-1)，则 dp[i][j] = 1 + dp[i-1][j-1]
        // 2.若 s.charAt(i-1) != p.char(j-1)，则 dp[i][j] = max(dp[i][j - 1],dp[i - 1][j])

        // 通过分析 dp[i][j] 依赖关系，，填表顺序，确定 for 循环次序
        // 行优先：从上往下 ↓，从左往右 →，注意区别：'行优先'和'列优先'
        // KeyPoint：注意：m 和 n 是可以取值到的，不要遗漏，否则 dp[m][n] = 0
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 注意：索引区别
                // 字符串索引，使用 i-1 和 j-1
                // 但是 dp 数组索引，使用 i 和 j
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    // 注意 1：
                    // 区间 [i,j] 范围 dp，是 dp[i][j] 与 dp[i+1][j-1]
                    // 但这里是两个字符串，逐一字符比较，是 dp[i][j] 与 dp[i-1][j-1]

                    // 注意 2：
                    // 最长公共子序列，text1 和 text2 字符相同算 1 字符，故 +1
                    // 最长回文子序列，首尾相同，算作 2 个字符，故 +2
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];

        // KeyPoint 经验总结：
        // 1.抬头和低头之间，代码很容易写错，需要特别小心。
        // 2.写代码时，紧紧盯着代码，避免代码误写
    }
}
