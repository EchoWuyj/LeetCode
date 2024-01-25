package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 0:06
 * @Version 1.0
 */
public class _15_20_isValid {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        // 存储 Character
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 进入 else 语句表示：c 为右括号的一种
                if (stack.isEmpty()) return false;
                // pop 对应左括号，因为左括号压栈
                char pop = stack.pop();
                // 判断 c 是具体那个右括号
                if (c == ')' && pop != '(') return false;
                if (c == ']' && pop != '[') return false;
                if (c == '}' && pop != '{') return false;
            }
        }
        // 最后不是直接返回 true，而是判断 stack 是否为空
        return stack.isEmpty();
    }
}
