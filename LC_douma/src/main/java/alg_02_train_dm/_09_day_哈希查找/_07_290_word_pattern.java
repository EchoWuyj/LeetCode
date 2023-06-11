package alg_02_train_dm._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:41
 * @Version 1.0
 */
public class _07_290_word_pattern {
     /*
        leetcode 290 号算法题：单词规律
        给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

        这里的 遵循 指完全匹配，
        例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

        输入: pattern = "abba", str = "dog cat cat dog"
        输出: true

        输入:pattern = "abba", str = "dog cat cat fish"
        输出: false

        输入: pattern = "aaaa", str = "dog cat cat dog"
        输出: false
     */

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> char2Word = new HashMap<>();
        Map<String, Character> word2Char = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            String word = words[i];
            // bug 修复：字符串需要使用 equals 方法比较，不能使用 == 判断是否相等
            if ((char2Word.containsKey(c) && !char2Word.get(c).equals(word)) ||
                    word2Char.containsKey(word) && !word2Char.get(word).equals(c)) {
                return false;
            }
            char2Word.put(c, word);
            word2Char.put(word, c);
        }
        return true;
    }
}
