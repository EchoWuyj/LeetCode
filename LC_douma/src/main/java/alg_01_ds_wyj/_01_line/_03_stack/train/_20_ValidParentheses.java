package alg_01_ds_wyj._01_line._03_stack.train;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 15:09
 * @Version 1.0
 */
public class _20_ValidParentheses {

    public boolean isValid1(String s) {
        if (s == null) return true;
        StringBuilder sb = new StringBuilder(s);
        int counts = sb.length() / 2;
        for (int i = 0; i < counts; i++) {
            for (int index = 0; index < sb.length() - 1; index++) {
                char c1 = sb.charAt(index);
                char c2 = sb.charAt(index + 1);
                if (isMatch(c1, c2)) {
                    sb.delete(index, index + 2);
                    break;
                }
            }
        }
        return sb.length() == 0;
    }

    public boolean isMatch(char c1, char c2) {
        if (c1 == '{') {
            return c2 == '}';
        } else if (c1 == '[') {
            return c2 == ']';
        } else if (c1 == '(') {
            return c2 == ')';
        } else {
            return false;
        }
    }

    public boolean isValid2(String s) {
        if (s == null) return true;
        if ((s.length() & 1) == 1) return false;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.poll();

                char matched = '#';
                if (c == ')') {
                    matched = '(';
                } else if (c == '}') {
                    matched = '{';
                } else {
                    matched = '[';
                }
                if (matched != top) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
