package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:08
 * @Version 1.0
 */
public class _05_1119_remove_vowels_from_a_string {
    public String removeVowels(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!isVowel(c)) res.append(c);
        }
        return res.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
