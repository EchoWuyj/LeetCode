package alg_02_train_wyj._29_day_动态规划四;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 19:28
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses2 {

    public int longestValidParentheses3(String s) {
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(i - stack.peek(), res);
                }
            }
        }
        return res;
    }
}
