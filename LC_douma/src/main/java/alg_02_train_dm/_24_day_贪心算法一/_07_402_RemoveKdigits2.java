package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:24
 * @Version 1.0
 */
public class _07_402_RemoveKdigits2 {
    // 时间复杂度：O(k + n)
    // 空间复杂度：O(n)
    public String removeKdigits(String num, int k) {
        // KeyPoint 双端队列 Deque
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // 固定 Deque 的 first 端进行进出，模拟栈效果
            while (!deque.isEmpty() && k > 0 && deque.peekFirst() > c) {
                // 从 first 出
                deque.pollFirst();
                k--;
            }
            // 从 first 进
            deque.addFirst(c);
        }
        for (int i = 0; i < k; i++) {
            deque.pollFirst();
        }

        // 优化
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        while (!deque.isEmpty()) {
            char c = deque.pollLast();
            // 跳过第一个 '0'
            if (c == '0' && isFirst) continue;
            sb.append(c);
            isFirst = false;
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _07_402_RemoveKdigits2().removeKdigits("10200", 1));
    }
}
