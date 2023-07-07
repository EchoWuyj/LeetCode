package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:47
 * @Version 1.0
 */
public class _07_290_word_pattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        //if (pattern.length() != words.length) return false;

        HashMap<String, Character> str2Char = new HashMap<>();
        HashMap<Character, String> char2Str = new HashMap<>();

        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (str2Char.containsKey(word) && !str2Char.get(word).equals(c)
                    || char2Str.containsKey(c) && !char2Str.get(c).equals(word)) {
                return false;
            }
            str2Char.put(word, c);
            char2Str.put(c, word);
        }
        return true;
    }
}
