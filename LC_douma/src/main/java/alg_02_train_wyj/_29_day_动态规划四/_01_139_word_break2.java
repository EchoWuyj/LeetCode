package alg_02_train_wyj._29_day_动态规划四;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 9:53
 * @Version 1.0
 */
public class _01_139_word_break2 {

    public boolean wordBreak3(String s, List<String> wordDict) {
        return dfs(s, s.length(),
                new HashSet<>(wordDict), new HashMap<>());
    }

    public boolean dfs(String s, int index,
                       HashSet<String> set, HashMap<Integer, Boolean> memo) {
        if (index == 0) return true;

        if (memo.containsKey(index)) return memo.get(index);

        for (int start = index - 1; start >= 0; start--) {
            if (!set.contains(s.substring(start, index))) continue;
            if (dfs(s, start, set, memo)) {
                return true;
            }
        }
        memo.put(index, false);
        return false;
    }

    public boolean wordBreak4(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (!set.contains(s.substring(j, i))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
