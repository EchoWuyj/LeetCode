package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 20:02
 * @Version 1.0
 */
public class _12_1143_LongestCommonSubsequence1 {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public int longestCommonSubsequence1(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        if (m < n) {
            return longestCommonSubsequence1(text2, text1);
        }

        int[][] dp = new int[2][n + 1];
        for (int i = 1; i <= m; i++) {
            int row = i % 2;
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[row][j] = 1 + dp[1 - row][j - 1];
                } else {
                    dp[row][j] = Math.max(dp[1 - row][j], dp[row][j - 1]);
                }
            }
        }
        return dp[m % 2][n];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m < n) {
            return longestCommonSubsequence2(text2, text1);
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = 0;
            int curr = 0;
            for (int j = 1; j <= n; j++) {
                prev = curr;
                curr = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + prev;
                } else {
                    dp[j] = Math.max(curr, dp[j - 1]);
                }
            }
        }
        return dp[n];
    }
}
