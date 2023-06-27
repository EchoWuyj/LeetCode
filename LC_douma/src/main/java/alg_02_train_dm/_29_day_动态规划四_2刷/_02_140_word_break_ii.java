package alg_02_train_dm._29_day_动态规划四_2刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:31
 * @Version 1.0
 */
public class _02_140_word_break_ii {
     /*
        140. 单词拆分 II
        给定一个 非空字符串 s 和一个包含 非空单词列表的字典 wordDict，
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

        KeyPoint 数据规模 => 时间复杂度
        1 <= s.length <= 20 => DFS 可以使用
        题目要求：返回所有这些可能的句子
        => 并不是最优值问题，是否存在问题，而是返回具体路径，必然是 DFS

     */

    // KeyPoint dfs + 剪枝 + 记忆化搜索 => 实现一
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, new HashSet<>(wordDict), 0, memo);
    }

    // dfs 后序遍历
    // 返回值 List，从当前节点到 root 节点，将该条路径中所有单词的加入 List 中
    // 题目要求返回句子，但是 dfs 可以有返回值， 也可以有返回值
    private List<String> dfs(String s, Set<String> set,
                             int index, HashMap<Integer, List<String>> memo) {

        // 每次递归调用 dfs，相当于访问一个新的节点
        // 创建一个 res 用来记录，当前节点 到 叶子节点 沿途的符合条件的单词
        List<String> res = new ArrayList<>();

        if (index == s.length()) {
            // index 越界位置，res 为""
            res.add("");
            return res;
        }

        // 查询缓存
        if (memo.containsKey(index)) return memo.get(index);

        for (int end = index + 1; end <= s.length(); end++) {
            String subStr = s.substring(index, end);
            // 提前剪枝
            if (!set.contains(subStr)) continue;

            // 子节点路径的单词列表
            List<String> list = dfs(s, set, end, memo);
            for (String str : list) {
                // 需要判断 str 是否为 ""，再去拼接，再加入 res 中
                res.add(subStr + (str.equals("") ? "" : " ") + str);

                // res.add(subStr + "".equals(str) ? "" : " " + str);
                // 其中 subStr + "" 会产生歧义，通过加括号()，来消除歧义
            }
        }

        // 返回前，存入缓存
        memo.put(index, res);

        // 返回 res
        return res;
    }

    // KeyPoint dfs + 剪枝 + 记忆化搜索 => 实现 2 => 推荐
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, new StringBuilder(),
                new HashSet<>(wordDict), new HashMap<>(), res);
        return res;
    }

    // dfs 前序遍历
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
}
