package alg_02_train_dm._26_day_动态规划一._10_516_LongestPalindromeSubseq;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 15:29
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq4 {
    // 动态规划
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 本题：斜右 ↘
        // 通过具体坐标索引例子，分析 i 和 j 之前关系，推导 for 循环抽象形式
        // 最大间隔 interval 为 n-1
        for (int interval = 1; interval < n; interval++) {
            for (int i = 0; i < n - 1; i++) {
                int j = i + interval;
                // j 越界，跳出内层循环
                if (j == n) break;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new _10_516_LongestPalindromeSubseq4()
                .longestPalindromeSubseq("bbbab"));
    }
}
