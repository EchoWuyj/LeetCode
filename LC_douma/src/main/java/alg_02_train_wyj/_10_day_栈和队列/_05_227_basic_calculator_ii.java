package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 20:40
 * @Version 1.0
 */
public class _05_227_basic_calculator_ii {
    public int calculate(String s) {
        char preSign = '+';
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) != ' ') {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                // "3+21-2*2+5/2"，因为默认是'+'，即 "+3|+21|-2|*2|+5|/2"
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    int top = stack.pop();
                    stack.push(top * num);
                } else if (preSign == '/') {
                    int top = stack.pop();
                    stack.push(top / num);
                }
                while (i < s.length() && s.charAt(i) == ' ') i++;
                if (i < s.length()) preSign = s.charAt(i);
            }
            i++;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
