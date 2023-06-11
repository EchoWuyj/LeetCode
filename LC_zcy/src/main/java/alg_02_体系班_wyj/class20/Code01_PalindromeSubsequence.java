package alg_02_体系班_wyj.class20;

/**
 * @Author Wuyj
 * @DateTime 2023-03-05 16:36
 * @Version 1.0
 */
public class Code01_PalindromeSubsequence {

    // 通过
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        StringBuilder str = new StringBuilder(s);
        StringBuilder reverseStr = str.reverse();
        return longestCommonSubsequence(s, reverseStr.toString());
    }

    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = (str1[0] == str2[0]) ? 1 : 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = (str1[i] == str2[0]) ? 1 : dp[i - 1][0];
        }

        for (int j = 1; j < m; j++) {
            dp[0][j] = (str1[0] == str2[j]) ? 1 : dp[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = (str1[i] == str2[j]) ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p3, Math.max(p1, p2));
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    public static int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = process(str, L + 1, R - 1);
        int p2 = process(str, L, R - 1);
        int p3 = process(str, L + 1, R);
        int p4 = (str[L] == str[R]) ? (2 + process(str, L + 1, R - 1)) : 0;
        return Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    }

    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];

        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = (str[i] == str[i + 1]) ? 2 : 1;
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int p1 = dp[i + 1][j - 1];
                int p2 = dp[i][j - 1];
                int p3 = dp[i + 1][j];
                int p4 = (str[i] == str[j]) ? (2 + dp[i + 1][j - 1]) : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][n - 1];
    }

    public static int lpsl3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];

        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = (str[i] == str[i + 1]) ? 2 : 1;
        }

        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(2 + dp[i + 1][j - 1], dp[i][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
