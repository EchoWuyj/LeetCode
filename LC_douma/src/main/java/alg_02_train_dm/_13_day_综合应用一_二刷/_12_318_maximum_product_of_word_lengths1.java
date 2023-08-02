package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:04
 * @Version 1.0
 */
public class _12_318_maximum_product_of_word_lengths1 {
     /* 
        318. 最大单词长度乘积
        给定一个字符串数组 words，找到 length(word[i]) * length(word[j])的最大值
        并且这两个单词不含有公共字母，你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。

        输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
        输出: 16

        输入: ["a","aa","aaa","aaaa"]
        输出: 0

        提示：
        2 <= words.length <= 1000
        1 <= words[i].length <= 1000
        words[i] 仅包含小写字母

     */

    // KeyPoint 方法一 暴力解法
    // m 表示单词的平均长度，n 表示单词的个数
    // hasSameChar1 => 时间复杂度：O(n^2 * m^2) => 超时
    // hasSameChar2 => 时间复杂度：O(n^2 * m) => 勉强通过
    // hasSameChar  => 时间复杂度：O(n^2 * m) => 勉强通过
    // 空间复杂度：O(1)
    public int maxProduct1(String[] words) {
        int res = 0;
        int n = words.length;
        // 1.两次 for 循环，影响性能，需要优化
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            // 变量是 j 不是 i，for 循环变量统一
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                // 2.判断两个单词是否有重复字符，影响性能，需要优化
                // 存在重复计算 => 每个单词 bitMask 会重复计算很多次
                if (!hasSameChar(word1, word2)) {
                    res = Math.max(res, word1.length() * word2.length());
                }
            }
        }
        return res;
    }

    // KeyPoint 判断两个 str 是否有重复字符 => 三种实现
    // O(m^2) 暴力
    private boolean hasSameChar1(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            // 返回指定字符在此字符串中，第一次出现处的索引，如果没有找到，就返回 -1
            if (word2.indexOf(c) != -1) return true;
        }
        return false;
    }

    // O(m) 统计词频
    private boolean hasSameChar2(String word1, String word2) {
        int[] count = new int[26];
        // 判断有无，将 count 值置为 1 即可，不用 count[c-'a']++
        for (char c : word1.toCharArray()) count[c - 'a'] = 1;
        for (char c : word2.toCharArray()) {
            // words[i] 仅包含小写字母，统计 word1 字符是否在 word2 出现
            // count 数组中，某个位置，count 值为 1 => 说明 word1 和 word2 有相同字符
            if (count[c - 'a'] == 1) return true;
        }
        return false;
    }

    // O(m) 二进制
    // 标记 0 和 1，通过一个 int 的二进制位来标记，更加节省空间
    private boolean hasSameChar(String word1, String word2) {
        // int 类型 bitMask1，一共是 32 位，有足够位置标记 0 和 1
        int bitMask1 = 0, bitMask2 = 0;
        // 一个字符，没有出现，使用 0 表示
        // 一个字符，出现，使用 1 表示
        // 直接使用 26 bit，表示每个字符是否出现
        for (char c : word1.toCharArray()) bitMask1 |= (1 << (c - 'a'));
        for (char c : word2.toCharArray()) bitMask2 |= (1 << (c - 'a'));

        // bitMask1 & bitMask2 == 0 => 说明'对位'都是不同的，则不在重复字符
        // KeyPoint 位运算，整体需要加上括号，提高运算的优先级，否则可能出现：运算符优先级的问题
        return (bitMask1 & bitMask2) != 0;
    }
}
