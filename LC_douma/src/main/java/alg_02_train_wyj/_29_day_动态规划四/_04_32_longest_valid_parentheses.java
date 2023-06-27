package alg_02_train_wyj._29_day_动态规划四;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-13 20:25
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses {

    public int longestValidParentheses1(String s) {
        int maxLen = 0;
        for (int end = 1; end <= s.length(); end++) {
            for (int start = 0; start < end; start++) {
                if (isValid1(s.substring(start, end))) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }
        return maxLen;
    }

    private boolean isValid(String str) {
        if (str.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isValid1(String str) {
        if (str.length() % 2 == 1) return false;
        int leftBraceCnt = 0;
        for (Character c : str.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt == 0) {
                    return false;
                } else {
                    leftBraceCnt--;
                }
            }
        }

        return leftBraceCnt == 0;
    }
}
