package alg_02_train_dm._13_day_综合应用一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:04
 * @Version 1.0
 */
public class _12_318_maximum_product_of_word_lengths {
     /* 
        318. 最大单词长度乘积
        给定一个字符串数组 words，找到 length(word[i]) * length(word[j])的最大值
        并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。
        如果不存在这样的两个单词，返回 0。

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
    // hasSameChar1 => 时间复杂度：O(n^2 * m^2)
    // hasSameChar2 => 时间复杂度：O(n^2 * m)
    // hasSameChar  => 时间复杂度：O(n^2 * m)
    // 空间复杂度：O(1)
    public int maxProduct1(String[] words) {
        int res = 0;
        // 1.两次 for 循环，影响性能，需要优化
        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            // 变量是 j 不是 i，for 循环变量统一
            for (int j = i + 1; j < words.length; j++) {
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

    // O(m^2) 暴力
    private boolean hasSameChar1(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            // 返回指定字符在此字符串中第一次出现处的索引，如果没有找到，就返回 -1
            if (word2.indexOf(c) != -1) return true;
        }
        return false;
    }

    // O(m) 统计词频
    private boolean hasSameChar2(String word1, String word2) {
        int[] count = new int[26];
        for (char c : word1.toCharArray()) count[c - 'a'] = 1;
        for (char c : word2.toCharArray()) {
            // words[i] 仅包含小写字母，统计 word1 字符是否在 word2 出现
            if (count[c - 'a'] == 1) return true;
        }
        return false;
    }

    // O(m) 二进制
    private boolean hasSameChar(String word1, String word2) {
        int bitMask1 = 0, bitMask2 = 0;
        // 一个字符，没有出现，使用 0 表示
        // 一个字符，出现，使用 1 表示
        // 直接使用 26 bit，表示每个字符是否出现
        for (char c : word1.toCharArray()) bitMask1 |= (1 << (c - 'a'));
        for (char c : word2.toCharArray()) bitMask2 |= (1 << (c - 'a'));

        // bitMask1 & bitMask2 == 0 => 说明'对位'都是不同的，则不在重复字符
        // 位运算，整体需要加上括号，提高运算的优先级
        return (bitMask1 & bitMask2) != 0;
    }

    // KeyPoint 方法二 位运算 + 预计算
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct2(String[] words) {
        // 通过预计算，提前将每个单词的 bitMask 计算出来，避免后面的重复计算
        // O(mn)
        int n = words.length;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            masks[i] = bitMask;
        }

        // O(n^2)
        int ans = 0;
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                // 相与为 0，说明对位分别为 1 和 0
                if ((masks[i] & masks[j]) == 0) {
                    ans = Math.max(ans, word1.length() * word2.length());
                }
            }
        }
        return ans;
    }

    // KeyPoint 方法三 位运算 + 预计算
    // 时间复杂度：O((m + n)* n)
    // 空间复杂度：O(n)
    public int maxProduct(String[] words) {
        // O(mn)
        Map<Integer, Integer> map = new HashMap<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            int bitMask = 0;
            for (char c : words[i].toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            // 不同的单词，可能有相同的 bitmask，但是只取最长的单词，从而减少比较次数
            // 如：ab and aabb
            map.put(bitMask, Math.max(map.getOrDefault(bitMask, 0), words[i].length()));
        }

        // O(n^2)
        // 时间复杂度没有降下来，但是实际上比较的次数有可能会降低
        // 在 OJ 上测试，时间效果并不理想，主要使用了 Map，影响了性能，同时数据集规模不大，测试不来算法性能
        int ans = 0;
        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                if ((x & y) == 0) {
                    ans = Math.max(ans, map.get(x) * map.get(y));
                }
            }
        }
        return ans;
    }
}
