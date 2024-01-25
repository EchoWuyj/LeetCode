package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 15:57
 * @Version 1.0
 */
public class _13_72_MinEditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m == 0 || n == 0) return m + n;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertCnt = 1 + dp[i][j - 1];
                    int deleteCnt = 1 + dp[i - 1][j];
                    int replaceCnt = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(insertCnt, deleteCnt), replaceCnt);
                }
            }
        }
        return dp[m][n];
    }
}
