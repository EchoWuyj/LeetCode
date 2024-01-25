package alg_02_train_dm._27_day_动态规划二_二刷;

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
         判定 s 是否可以被 空格 拆分为 一个 或 多个 在字典中出现的单词。

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

    // KeyPoint 转换问题方向
    // => 在 wordDict 中，可重复的选择字符串(字符串整体，不能拆分成字符)组合，
    //    看看是否存在可以组成字符串 s 的组合
    // => 完全背包问题
    // => 组合问题 => 关于是否存在，使用 boolean；
    //               若是个数，使用 int
    public boolean wordBreak(String s, List<String> wordDict) {

        // 物品：wordDict 中的单词
        // 背包容量：s 字符串长度
        // 目标：wordDict 中的单词是否组合成功 s 字符串

        // dp[i]: 表示前 i 个字符组成的子串，是否可以被 wordDict 中的字符串组合而成
        // 背包容量：字符串长度，前 1，2，3，i 字符
        boolean[] dp = new boolean[s.length() + 1];

        // 0 个字符，不选 wordDict 中的字符串即可
        dp[0] = true;

        // KeyPoint 注意：本题中组合的顺序是无序的
        // s = "leetcode"，wordDict = ["leet", "code"]，返回 true
        // s = "codeleet"，wordDict = ["leet", "code"]，返回 true

        // KeyPoint 相反：硬币找零问题，组合是有顺序，前后顺序影响结果

        // 故需要调整内外层 for 循环
        // 1.先选择字符长度(容量)
        // 2.再选择每个字符串(物品)
        // KeyPoint 区别：常规情况：先物品，再容量

        // KeyPoint 容量对应循环变量 j
        for (int j = 1; j <= s.length(); j++) {
            // wordDict 选择单词
            for (String str : wordDict) {
                int strLen = str.length();

                // KeyPoint 关键点
                // 前 j 个字符，本身是不包括 j 位置的，substring 中 j 也是不包括的
                // 补充说明：前 j 个字符
                // 若 j = 3，表示前 3 个字符，其实指的是 0,1,2，本身是不包括的 j 的
                // a b c d
                // 0 1 2 3

                // j >= strLen 保证 dp[j - strLen] 不越界
                // s 截取 strLen 长度字符能够匹配上，才去判断后续 dp[j]，否则直接跳过
                // KeyPoint 注意
                // j 表示前 j 个字符，j 为结束位置，往前倒推长度 strLen，从而保证截取字符串长度为 strLen
                if (j >= strLen && s.substring(j - strLen, j).equals(str)) {
                    // 是否存在问题，使用 || 或者的关系
                    // 选择 或者 不选择
                    dp[j] = dp[j] || dp[j - strLen];
                }
            }
        }
        return dp[s.length()];
    }
}
