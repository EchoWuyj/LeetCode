package alg_02_train_wyj._27_day_动态规划二;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 20:47
 * @Version 1.0
 */
public class _14_139_word_break {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int j = 0; j <= s.length(); j++) {
            for (String str : wordDict) {
                int strLen = str.length();
                if (j >= strLen && s.substring(j - strLen, j).equals(str)) {
                    dp[j] = dp[j] || dp[j - strLen];
                }
            }
        }
        return dp[len];
    }
}
