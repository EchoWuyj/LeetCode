package alg_02_train_wyj._29_day_动态规划四;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-13 16:04
 * @Version 1.0
 */
public class _02_140_word_break_ii {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, new HashSet<String>(wordDict), 0, memo);
}

    private List<String> dfs(String s, HashSet<String> set,
                             int index, HashMap<Integer, List<String>> memo) {
        ArrayList<String> res = new ArrayList<>();
        if (index == s.length()) {
            res.add("");
            return res;
        }
        if (memo.containsKey(index)) return memo.get(index);
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (!set.contains(word)) continue;
            List<String> list = dfs(s, set, i, memo);
            for (String str : list) {
                res.add(word + ("".equals(str) ? "" : " ") + str);
            }
        }
        memo.put(index, res);
        return res;
    }
}
