package alg_02_train_dm._26_day_动态规划一._12_1143_LongestCommonSubsequence;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 19:46
 * @Version 1.0
 */
public class _12_1143_LongestCommonSubsequence1 {

    /*
        1143. 最长公共子序列
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

        // 1.若 s.charAt(i-1) == p.char(j-1)，则 dp[i][j] = 1 + dp[i-1][j-1]
        // 2.若 s.charAt(i-1) != p.char(j-1)，则 dp[i][j] = max(dp[i][j - 1],dp[i - 1][j])

        // 填表顺序 => for 循环顺序
        // 行优先：从上往下 ↓，从左往右 →，注意区别：'行优先'和'列优先'
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 分析依赖关系，确定 for 循环次序
                    dp[i][j] = 1 + dp[i - 1][j - 1];
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

    // KeyPoint 方法二 压缩状态(一)
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        // => 保证二维 dp 数组(矩阵)竖向，这样使用的列数较少，且两行，从而节省空间
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        // 当前的状态依赖于前一行和当前行两行的状态值
        int[][] dp = new int[2][n + 1];

        for (int i = 1; i <= m; i++) {
            int currRow = i % 2;
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 单个 i 位置，直接使用 currRow 替换
                    // i-1 => 1-currRow，而不能直接 currRow-1，否则越界，保证 i 取值，只能在 0 和 1 之间
                    dp[currRow][j] = 1 + dp[1 - currRow][j - 1];
                } else {
                    dp[currRow][j] =
                            Math.max(dp[currRow][j - 1], dp[1 - currRow][j]);
                }
            }
        }
        // 根据 m 的长度来返回 dp，即：当 i = m 时，currRow = m % 2;
        return dp[m % 2][n];
    }

    // KeyPoint 方法三 压缩状态(二) => 确实能提高性能
    public int longestCommonSubsequence3(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        // 当前的状态依赖于前一行和当前行的状态值
        // 若想要压缩状态，即使用一维数组来代替二维数组，则将前一行状态记录在两个变量中，从而减少一行
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prev = 0;
            int curr = 0;
            for (int j = 1; j <= n; j++) {
                curr = prev;
                // for 循环一轮之后，dp 数组已经变成上一行状态数组，dp[j] 为对应位置状态值
                prev = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + curr;
                } else {
                    dp[j] = Math.max(prev, dp[j - 1]);
                }
            }
        }

        return dp[n];
    }
}
