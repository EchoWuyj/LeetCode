package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 20:16
 * @Version 1.0
 */
public class _33_72_minDistance {

    // 编辑距离
    // 动态规划
    public int minDistance(String word1, String word2) {

        // 特判
        int m = word1.length();
        int n = word2.length();
        if (m == 0 || n == 0) {
            return m + n;
        }

        // dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数，即编辑距离
        int[][] dp = new int[m + 1][n + 1];

        // i 对应 m
        // i 不为 0，j = 0 情况
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // j 对应 n
        // j 不为 0，i = 0 情况
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 从二维矩阵中 [1,1] 位置开始
        // i 和 j 都从 1 开始，并且 m 和 n 都能取等
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // KeyPoint 注意：索引位置 i-1 和 j-1，而不是 i 和 j
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // insertCt 和 i 保持一致，所以 i 不变化，只能是 j - 1
                    int insertCt = 1 + dp[i][j - 1];
                    int deleteCt = 1 + dp[i - 1][j];
                    // replaceCt 替换改动大，i 和 j 都要变化
                    int replaceCt = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insertCt, Math.min(deleteCt, replaceCt));
                }
            }
        }
        // 最终状态
        return dp[m][n];
    }
}
