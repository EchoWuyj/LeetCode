package alg_02_train_wyj._29_day_动态规划四;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-13 16:56
 * @Version 1.0
 */
public class _03_91_decode_ways {

    public int numDecodings(String s) {
        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(s, 0, memo);
    }

    private int dfs(String s, int index, int[] memo) {
        if (index == s.length()) return 1;
        if (memo[index] != -1) return memo[index];
        if (s.charAt(index) == '0') return 0;
        int res = 0;
        res += dfs(s, index + 1, memo);
        if (index < s.length() - 1) {
            int one = s.charAt(index + 1) - '0';
            int ten = (s.charAt(index) - '0') * 10;
            if (one + ten <= 26) {
                res += dfs(s, index + 2, memo);
            }
        }
        memo[index] = res;
        return memo[index];
    }

    public int numDecodings1(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i + 1];
                if (i < n - 1) {
                    int one = s.charAt(i + 1) - '0';
                    int ten = (s.charAt(i) - '0') * 10;
                    if (one + ten <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }
}
