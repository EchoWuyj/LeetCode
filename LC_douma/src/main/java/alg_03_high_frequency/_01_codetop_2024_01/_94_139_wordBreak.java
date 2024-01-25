package alg_03_high_frequency._01_codetop_2024_01;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _94_139_wordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j <= n; j++) {
            for (String word : wordDict) {
                int len = word.length();
                if (j >= len && s.substring(j - len, j).equals(word)) {
                    dp[j] = dp[j] || dp[j - len];
                }
            }
        }
        return dp[n];
    }
}
