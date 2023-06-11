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
        StringBuilder res = new StringBuilder(s);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < res.length(); i++) {
            if (i == 0 || res.charAt(i) != res.charAt(i - 1)) {
                stack.push(1);
            } else {
                int counts = stack.pop() + 1;
                if (counts == k) {
                    res.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    stack.push(counts);
                }
            }
        }
        return res.toString();
    }

    public String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        // 使用栈计数
        Deque<Integer> stack = new ArrayDeque<>();
        int slow = 0;
        for (int fast = 0; fast < s.length(); slow++, fast++) {
            chars[fast] = chars[slow];
            if (fast == 0 || chars[fast] != chars[fast - 1]) {
                stack.push(1);
            } else {
                int count = stack.pop() + 1;
                if (count == k) {
                    fast = fast - k;
                } else {
                    stack.push(count);
                }
            }
        }
        return new String(chars, 0, slow);
    }
}
