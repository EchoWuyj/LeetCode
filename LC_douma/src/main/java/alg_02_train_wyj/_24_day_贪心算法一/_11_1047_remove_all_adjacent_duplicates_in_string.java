package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 19:28
 * @Version 1.0
 */
public class _11_1047_remove_all_adjacent_duplicates_in_string {
    public String removeDuplicates1(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : chars) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollLast());
        }
        return res.toString();
    }

    public String removeDuplicates2(String s) {
        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder();
        for (char c : chars) {
            if (res.length() > 0 && res.charAt(res.length() - 1) == c) {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int slow = -1, fast = 0;
        while (fast < s.length()) {
            if (slow >= 0 && chars[slow] == chars[fast]) {
                slow--;
            } else {
                slow++;
                chars[slow] = chars[fast];
            }
            fast++;
        }
        // char[] charArray = {'h', 'e', 'l', 'l', 'o'}; 比如 String(charArray, 1, 3)
        // 将返回一个新字符串对象 "ell"，因为它从字符数组中的索引 1，即第二个字符 'e' 开始，包含 3 个字符
        // 索引从 0 开始，故 slow + 1，表示个数
        return new String(chars, 0, slow + 1);
    }
}
