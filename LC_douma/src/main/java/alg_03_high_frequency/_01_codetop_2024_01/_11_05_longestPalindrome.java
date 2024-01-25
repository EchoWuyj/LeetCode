package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 21:08
 * @Version 1.0
 */
public class _11_05_longestPalindrome {
    public String longestPalindrome(String s) {

        // 特判
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;

        int n = s.length();
        // 状态：boolean 值
        // dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        boolean[][] dp = new boolean[n][n];

        // 单个元素都是回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // res 保底
        String res = s.charAt(0) + "";

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 两种情况
                if (j - i == 1) {
                    // 判断两个字符是否相等
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    // 当前字符 && 之前的 dp[][]
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }

                // 更新
                if (dp[i][j] && (j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }
}
