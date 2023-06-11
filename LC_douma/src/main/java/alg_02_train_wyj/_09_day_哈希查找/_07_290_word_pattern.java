package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:47
 * @Version 1.0
 */
public class _07_290_word_pattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> char2Word = new HashMap<>();
        Map<String, Character> word2Char = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (char2Word.containsKey(c) && !char2Word.get(c).equals(word)
                    || word2Char.containsKey(word) && !word2Char.get(word).equals(c)) {
                return false;
            }
            char2Word.put(c, word);
            word2Char.put(word, c);
        }
        return true;
    }
}
