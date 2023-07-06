package alg_02_train_dm._29_day_动态规划四_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 15:50
 * @Version 1.0
 */
public class _03_91_decode_ways2 {

    // KeyPoint 方法二 dfs + 记忆化搜索 => 了解即可
    public int numDecodings3(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return dfs2(s, s.length(), memo);
    }

    // 以第 i 个字符结尾的子串能解码的个数
    private int dfs2(String s, int i, int[] memo) {
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];

        int res = 0;
        if (s.charAt(i - 1) != '0') {
            res += dfs2(s, i - 1, memo);
        }
        if (i > 1 && s.charAt(i - 2) != '0') {
            int one = s.charAt(i - 1) - '0';
            int ten = (s.charAt(i - 2) - '0') * 10;
            if (one + ten <= 26) {
                res += dfs2(s, i - 2, memo);
            }
        }

        memo[i] = res;
        return memo[i];
    }

    // KeyPoint 动态规划(从左往右)
    public int numDecodings4(String s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符结尾的子串能解码的个数
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1 && s.charAt(i - 2) != '0') {
                int one = s.charAt(i - 1) - '0';
                int ten = (s.charAt(i - 2) - '0') * 10;
                if (one + ten <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}
