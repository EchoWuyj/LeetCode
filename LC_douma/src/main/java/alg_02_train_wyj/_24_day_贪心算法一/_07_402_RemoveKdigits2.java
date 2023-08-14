package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 18:41
 * @Version 1.0
 */
public class _07_402_RemoveKdigits2 {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && c < deque.peekFirst()) {
                k--;
                deque.pollFirst();
            }
            deque.offerFirst(c);
        }

        while (k > 0) {
            deque.pollFirst();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        while (!deque.isEmpty()) {
            char c = deque.pollLast();
            if (c == '0' && isFirst) continue;
            sb.append(c);
            isFirst = false;
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
