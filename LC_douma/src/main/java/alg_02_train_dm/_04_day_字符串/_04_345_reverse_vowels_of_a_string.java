package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:08
 * @Version 1.0
 */
public class _04_345_reverse_vowels_of_a_string {
    public String reverseVowels(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !isVowel(chars[left])) left++;
            while (left < right && !isVowel(chars[right])) right--;

            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }
        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
