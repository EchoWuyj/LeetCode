package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 18:00
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string {
    // KeyPoint 方法一
    public String reverseWords1(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    // KeyPoint 方法二
    public static String reverseWords2(String s) {
        s = trimSpaces(s.toCharArray());
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        System.out.println(new String(chars));
        s = reverseEachWord(chars);
        return s;
    }

    private static String trimSpaces(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left <= right && chars[left] == ' ') left++;
        while (left <= right && chars[right] == ' ') right--;
        StringBuilder res = new StringBuilder();
        while (left <= right) {
            char cur = chars[left];
            if (cur != ' ') {
                res.append(cur);
            } else if (res.charAt(res.length() - 1) != ' ') {
                res.append(' ');
            }
            left++;
        }
        return res.toString();
    }

    private static String trimSpaces1(char[] chars) {
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

    public static void main(String[] args) {
        String s = "  Bob    Loves  Alice   ";
        System.out.println(trimSpaces1(s.toCharArray()));
    }

    private static String reverseEachWord(char[] chars) {
        int left = 0;
        int n = chars.length;
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right + 1 < n && chars[right + 1] != ' ') right++;
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    private static void reverse(char[] chars, int i, int j) {
        char tmp;
        while (i < j) {
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;
        ArrayDeque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char cur = s.charAt(left);
            if (cur != ' ') {
                word.append(cur);
            } else if (word.length() != 0) {
                deque.offerFirst(word.toString());
                word.setLength(0);
            }
            left++;
        }
        deque.offerFirst(word.toString());
        String res = String.join(" ", deque);
        return res;
    }
}
