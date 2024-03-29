package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-11-20 14:46
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string2 {

    // KeyPoint 方法二 => 推荐使用
    public String reverseWords2(String s) {
        s = trimSpace(s);
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        String res = reverseEachSWord(chars);
        return res;
    }

    private String trimSpace(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else {
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
            }
            left++;
        }
        return sb.toString();
    }

    private void reverse(char[] chars, int i, int j) {
        char tmp;
        while (i < j) {
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    // KeyPoint 关键
    private String reverseEachSWord(char[] chars) {
        int left = 0;
        int n = chars.length;
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right < n - 1 && chars[right + 1] != ' ') right++;
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    // ========================================

    private String trimSpace1(char[] chars) {
        int n = chars.length;
        int slow = 0, fast = 0;
        while (fast < n) {
            while (fast < n && chars[fast] == ' ') fast++;
            while (fast < n && chars[fast] != ' ') chars[slow++] = chars[fast++];
            while (fast < n && chars[fast] == ' ') fast++;
            if (fast < n) chars[slow++] = ' ';
        }
        return new String(chars).substring(0, slow);
    }

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else {
                if (word.length() != 0) {
                    deque.offerFirst(word.toString());
                    word.setLength(0);
                }
            }
            left++;
        }
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }
}
