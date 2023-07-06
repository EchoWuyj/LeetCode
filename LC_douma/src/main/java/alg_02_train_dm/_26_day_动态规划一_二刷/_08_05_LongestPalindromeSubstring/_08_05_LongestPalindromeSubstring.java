package alg_02_train_dm._26_day_动态规划一_二刷._08_05_LongestPalindromeSubstring;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 11:48
 * @Version 1.0
 */
public class _08_05_LongestPalindromeSubstring {
    /*
        5. 最长回文子串
        给你一个字符串 s，找到 s 中最长的回文子串。
        如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

        示例 1：
        输入：s = "babad"
        输出："bab"
        解释："aba" 同样是符合题意的答案。
        示例 2：

        输入：s = "cbbd"
        输出："bb"

        提示：
        1 <= s.length <= 1000
        s 仅由数字和英文字母组成

     */

    // 动态规划
    // 时间复杂度：O(n^2)
    public String longestPalindrome(String s) {
        // 使用 dp，需要进行特判
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;

        int n = s.length();
        // 定义状态
        // dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        boolean[][] dp = new boolean[n][n];

        String res = s.charAt(0) + "";

        // 状态初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // 一个字符，肯定是回文
        }

        // 状态转移
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 只有两个字符
                if (j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    // 大于两个字符
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                // dp[i][j] 保证为 true，且长度更长
                // j-i，还得 +1，表示数组两端都包括在内
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}
