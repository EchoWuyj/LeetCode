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
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!deque.isEmpty() && c == deque.peekFirst()) {
                deque.pollFirst();
            } else {
                deque.offerFirst(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }

    public String removeDuplicates2(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
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
        int n = s.length();
        while (fast < n) {
            if (slow >= 0 && chars[slow] == chars[fast]) {
                slow--;
            } else {
                slow++;
                chars[slow] = chars[fast];
            }
            fast++;
        }
        return new String(chars, 0, slow + 1);
    }
}
