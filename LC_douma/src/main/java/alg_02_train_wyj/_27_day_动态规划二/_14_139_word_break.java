package alg_02_train_wyj._27_day_动态规划二;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 20:47
 * @Version 1.0
 */
public class _14_139_word_break {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && s.substring(i - len, i).equals(word)) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
        return dp[s.length()];
    }
}
