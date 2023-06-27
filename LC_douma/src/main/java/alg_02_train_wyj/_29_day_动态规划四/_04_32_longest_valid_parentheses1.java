package alg_02_train_wyj._29_day_动态规划四;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 19:27
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses1 {
    public int longestValidParentheses2(String s) {
        int n = s.length();
        if (n <= 1) return 0;
        int[] dp = new int[n];
        dp[0] = 0;

        if (s.charAt(0) == '(' && s.charAt(1) == ')') dp[1] = 2;
        int maxLen = dp[1];

        for (int i = 2; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
}
