package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 17:52
 * @Version 1.0
 */
public class _07_402_RemoveKdigits1 {
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && c < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        n = sb.length();
        while (n != 0) {
            if (sb.charAt(0) > '0') break;
            sb.deleteCharAt(0);
            n = sb.length();
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
