package alg_02_train_dm._29_day_动态规划四;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:31
 * @Version 1.0
 */
public class _02_140_word_break_ii {
     /*
        140. 单词拆分 II
        给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
        在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
        返回所有这些可能的句子。

        说明：
        分隔时可以重复使用字典中的单词。
        你可以假设字典中没有重复的单词。

        示例 1：
        输入:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]
        输出:
        [
          "cats and dog",
          "cat sand dog"
        ]

        示例 2：
        输入:
        s = "pineapplepenapple"
        wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        输出:
        [
          "pine apple pen apple", => 可以重复使用字典中的单词
          "pineapple pen apple",
          "pine applepen apple"
        ]
        解释: 注意你可以重复使用字典中的单词。

        示例 3：
        输入:
        s = "catsandog"
        wordDict = ["cats", "dog", "sand", "and", "cat"]
        输出:
        []

        提示：
        1 <= s.length <= 20
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 10
        s 和 wordDict[i] 仅有小写英文字母组成
        wordDict 中所有字符串都 不同

     */

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, new HashSet<>(wordDict), 0, memo);
    }

    // 题目要求返回句子，故是有返回值的，故 dfs 也得是有返回值 (dfs 后序遍历)
    // 返回值 List，从当前节点到 root 节点，将该条路径中所有单词的加入 List 中
    private List<String> dfs(String s, Set<String> wordDict,
                             int index, HashMap<Integer, List<String>> memo) {
        // 每次递归调用，相当于访问一个新的节点
        // 创建一个 res 用来记录，当前节点到 root 节点沿途的符合条件的单词
        List<String> res = new ArrayList<>();

        if (index == s.length()) {
            res.add("");
            return res;
        }

        // 查询缓存
        if (memo.containsKey(index)) return memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            String word = s.substring(index, end);
            // 提前剪枝
            if (!wordDict.contains(word)) continue;

            // 子节点路径单词列表
            List<String> list = dfs(s, wordDict, end, memo);
            for (String str : list) {
                //word 和 str 拼接，再加入 res 中
                res.add(word + (str.equals("") ? "" : " ") + str);

                // res.add(word + ("".equals(str) ? "" : " ") + str);
                // word + "" 会产生歧义，通过加括号()，来消除歧义
            }
        }

        // 返回前，存入缓存
        memo.put(index, res);

        // 返回 res
        return res;
    }
}
