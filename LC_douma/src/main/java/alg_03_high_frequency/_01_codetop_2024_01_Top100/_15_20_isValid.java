package alg_03_high_frequency._01_codetop_2024_01_Top100;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 0:06
 * @Version 1.0
 */
public class _15_20_isValid {

    // 有效的括号
    // 栈
    public boolean isValid(String s) {
        // 特判
        if (s.length() % 2 == 1) return false;
        // 存储 Character
        // Deque 和 Queue 单词类似，两者都是以 ue 结尾
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                // 左括号入栈
                stack.push(c);
                // 进入 else 语句表示：c 为右括号的一种
            } else {
                // 有右括号，没有左括号，不是有效括号
                if (stack.isEmpty()) return false;
                // pop 对应 左括号，因为压栈中都是左括号
                char pop = stack.pop();
                // 先确定 c，再去确定 pop，若是满足不匹配，直接返回 false
                if (c == ')' && pop != '(') return false;
                if (c == ']' && pop != '[') return false;
                if (c == '}' && pop != '{') return false;
            }
        }
        // KeyPoint 易错点
        // 最后不是直接返回 true，而是判断 stack 是否为空
        // 如果 '((())'，经过匹配操作之后，消除 '(())'，最后 stack 中剩余 '('，不是有效括号
        return stack.isEmpty();
    }
}
