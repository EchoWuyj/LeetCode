package alg_02_train_dm._27_day_动态规划二;

import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 12:44
 * @Version 1.0
 */
public class _14_139_word_break {
    /*
         139. 单词拆分
         给定一个 非空字符串 s 和一个包含 非空单词的列表 wordDict，
         判定 s 是否可以被空格拆分为 一个 或 多个 在字典中出现的单词。

         说明：
         拆分时可以重复使用字典中的单词。
         你可以假设字典中没有重复的单词。

         示例 1：
         输入: s = "leetcode", wordDict = ["leet", "code"]
         输出: true
         解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。

         示例 2：
         输入: s = "applepenapple", wordDict = ["apple", "pen"]
         输出: true
         解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
               注意你可以重复使用字典中的单词。

         示例 3：
         输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
         输出: false

         提示：
         1 <= s.length <= 300
         1 <= wordDict.length <= 1000
         1 <= wordDict[i].length <= 20
         s 和 wordDict[i] 仅有小写英文字母组成
         wordDict 中的所有字符串 互不相同

      */

    // 转换问题方向
    // => 在 wordDict 中，可重复的选择字符串组合，看看是否存在可以组成字符串 s 的组合
    // => 完全背包问题

    // 物品：单词数组
    // 背包容量：字符串
    // 背包价值：单词是否组合成功字符串

    // dp[i][c],表示前 i 个单词，是否可以组成长度为 c 的字符串，
    // 由于每个字符串都需所有的单词组合一遍，因此将背包重量的循环放到物品个数的循环之前。

    // 每个物品都有放入和不放入的情况
    // 当物品不放入背包：dp[i][c]=dp[i - 1][c]
    // 当物品放入背包：dp[i][c]=dp[i][c - w.size()]

    // 压缩物品维度
    public boolean wordBreak(String s, List<String> wordDict) {

        // 物品 => wordDict 中字符串
        // 背包容量 => i 个字符串长度

        // dp[i]: 表示 前 i 个字符 组成的子串是否可以被 wordDict 中的字符串组合而成
        // 背包容量：字符串长度，前 1，2，3，i 字符
        boolean[] dp = new boolean[s.length() + 1];

        // 补充说明：前 i 个字符
        // 若 i = 3，表示前 3 个字符，其实指的是 0,1,2，本身是不包括的 i 的
        // a b c d
        // 0 1 2 3

        // 0 个字符，不选 wordDict 中的字符串即可
        dp[0] = true;

        // 注意：本题中组合的顺序是任意的
        // s = "leetcode" => s = "codeleet"，wordDict = ["leet", "code"]
        // 调整内外层 for 循环：所以先选择字符长度(背包容量)，再选择每个字符串
        for (int i = 1; i <= s.length(); i++) {
            // wordDict 选择单词
            for (String word : wordDict) {
                int wordLen = word.length();
                // 前 i 个字符，本身是不包括 i 位置的，substring 中 i也是不包括的
                if (i >= wordLen && s.substring(i - wordLen, i).equals(word)) {
                    // 是否存在，使用 || 或者的关系
                    dp[i] = dp[i] || dp[i - wordLen];
                }
            }
        }
        return dp[s.length()];
    }
}
