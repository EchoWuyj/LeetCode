package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:24
 * @Version 1.0
 */
public class _07_402_RemoveKdigits2 {

    // KeyPoint 方法三 贪心 + 双端队列
    // 使用双端队列，操作上比使用栈更加灵活，其两端都可以进出操作
    // 时间复杂度：O(k + n)
    // 空间复杂度：O(n)
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            // 固定 Deque 的 first 端进行进出，模拟栈效果
            // => Deque 中 first 端，对应栈顶
            while (!deque.isEmpty() && k > 0 && deque.peekFirst() > c) {
                // 从 first 出
                deque.pollFirst();
                k--;
            }
            // 从 first 进
            deque.offerFirst(c);
        }
        for (int i = 0; i < k; i++) {
            deque.pollFirst();
        }

        // 优化
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        while (!deque.isEmpty()) {
            // first => 栈顶
            // last => 栈底
            char c = deque.pollLast();
            // 栈底第一元素，isFirst 为 true，
            // 且 c 若为 '0'，直接跳过
            // => 跳过第一个 '0'
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
