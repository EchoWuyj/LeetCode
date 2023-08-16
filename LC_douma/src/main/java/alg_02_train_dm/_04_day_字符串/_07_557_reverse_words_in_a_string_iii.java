package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:11
 * @Version 1.0
 */
public class _07_557_reverse_words_in_a_string_iii {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int left = 0;
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right + 1 < n && chars[right + 1] != ' ') right++;
                reverseWord(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    private void reverseWord(char[] cArr, int start, int end) {
        char temp;
        while (start < end) {
            temp = cArr[start];
            cArr[start++] = cArr[end];
            cArr[end--] = temp;
        }
    }
}
