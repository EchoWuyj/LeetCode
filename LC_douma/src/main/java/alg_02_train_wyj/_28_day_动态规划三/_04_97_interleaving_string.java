package alg_02_train_wyj._28_day_动态规划三;

/**
 * @Author Wuyj
 * @DateTime 2023-06-11 18:31
 * @Version 1.0
 */
public class _04_97_interleaving_string {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int k = i + j;
                boolean s1Equals3 = s1.charAt(i - 1) == s3.charAt(k - 1) && dp[i - 1][j];
                boolean s2Equals3 = s2.charAt(j - 1) == s3.charAt(k - 1) && dp[i][j - 1];
                dp[i][j] = s1Equals3 || s2Equals3;
            }
        }
        return dp[m][n];
    }
}
