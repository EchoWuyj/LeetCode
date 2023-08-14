package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 21:08
 * @Version 1.0
 */
public class _12_1209_remove_all_adjacent_duplicates_in_string_ii {
    public String removeDuplicates1(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        int n = sb.length();
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                stack.push(1);
            } else {
                int count = stack.poll() + 1;
                if (count == k) {
                    sb.delete(i + 1 - k, i + 1);
                    i = i - k;
                } else {
                    stack.push(count);
                }
            }
        }
        return sb.toString();
    }

    public String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++, slow++) {
            if (slow != fast) chars[slow] = chars[fast];
            if (slow == 0 || chars[slow] != chars[slow - 1]) {
                stack.push(1);
            } else {
                int count = stack.pop() + 1;
                if (count == k) {
                    slow = slow - k;
                } else {
                    stack.push(count);
                }
            }
        }
        return new String(chars, 0, slow);
    }
}
