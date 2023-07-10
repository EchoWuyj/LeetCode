package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:41
 * @Version 1.0
 */
public class _07_290_word_pattern {
     /*
        290 号算法题：单词规律
        给定一种规律 pattern 和一个字符串 str，判断 str 是否遵循相同的规律。
        这里的 遵循 指完全匹配，
        例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着 双向连接 的对应规律。

        输入: pattern = "abba", str = "dog cat cat dog"
        输出: true

        输入:pattern = "abba", str = "dog cat cat fish"
        输出: false

        输入: pattern = "aaaa", str = "dog cat cat dog"
        输出: false
        
        提示:
        1 <= pattern.length <= 300
        pattern只包含小写英文字母
        1 <= s.length <= 3000
        s 只包含小写英文字母和' '
        s 不包含 任何前导或尾随对空格
        s 中每个单词都被 单个空格 分隔

     */

    // 本质：205 同构字符串
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        // 个数不同，直接 return false
        // 需要加上，否则测试用例，报错
        if (pattern.length() != words.length) return false;

        Map<Character, String> char2Word = new HashMap<>();
        Map<String, Character> word2Char = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            String word = words[i];
            // KeyPoint 字符串需要使用 equals 方法比较，不能使用 == 判断是否相等
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
