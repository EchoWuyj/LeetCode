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

    // dfs + 剪枝 + 记忆化搜索
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, new StringBuilder(),
                new HashSet<>(wordDict), new HashMap<>(), res);
        return res;
    }

    public static void dfs(String s, int index,
                           StringBuilder sb, HashSet<String> set,
                           HashMap<Integer, String> memo,
                           List<String> res) {
        if (index == s.length()) {
            // 不删除 sb 最后一个 " "，避免 sb.delete 索引越界
            String subStr = sb.toString();
            // 通过 trim() 来消除 "cat sand dog " 最后的空格 " "
            res.add(subStr.trim());
            return;
        }

        if (memo.containsKey(index)) memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            String subStr = s.substring(index, end);
            int strLen = subStr.length();
            if (!set.contains(subStr)) continue;
            memo.put(index, subStr);
            sb.append(subStr).append(" ");
            dfs(s, end, sb, set, memo, res);
            // 回溯，还原现场
            // sb.delete(0,3) 删除字符的长度：end -start = 3
            // 实际包含字符索引为：0,1,2，长度也是 3
            sb.delete(sb.length() - strLen - 1, sb.length());
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, 0,
                new HashSet<>(wordDict),
                new HashMap<>());
    }

    public List<String> dfs(String s, int index,
                            HashSet<String> set,
                            HashMap<Integer, List<String>> memo) {
        ArrayList<String> res = new ArrayList<>();
        if (index == s.length()) {
            res.add("");
            return res;
        }

        if (memo.containsKey(index)) memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            String subStr = s.substring(index, end);
            if (!set.contains(subStr)) continue;
            List<String> list = dfs(s, end, set, memo);
            for (String str : list) {
                res.add(subStr + (str.equals("") ? "" : " ") + str);
            }
        }
        memo.put(index, res);
        return res;
    }
}
