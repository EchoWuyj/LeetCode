package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 21:37
 * @Version 1.0
 */
public class _36_1143_longestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        // 类似于编辑距离
        int[][] dp = new int[m + 1][n + 1];

        // 没有初始化 dp,则 dp 默认初始化为 0，故将其进行省略了

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 类似于：编辑距离
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 注意区别
                    // 1.两个字符串之间的比较，都是 dp[i-1][j-1]
                    // 2.同一个字符串前后比较，都是 dp[i+1][j-1]
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // 从两端中获取最大值
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
