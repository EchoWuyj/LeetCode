package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 20:40
 * @Version 1.0
 */
public class _05_227_basic_calculator_ii {
    public int calculate(String s) {
        char preSign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) != ' ') {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }

                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    int pop = stack.pop();
                    stack.push(pop * num);
                } else if (preSign == '/') {
                    int pop = stack.pop();
                    stack.push(pop / num);
                }

                while (i < n && s.charAt(i) == ' ') i++;
                if (i < n) preSign = s.charAt(i);
            }
            i++;
        }
        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    public int calculate2(String s) {
        char preSign = '+';
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // "13+21-2*2+5/2"
        // "3+2*2"
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                // 跳出 while 循环时，s.charAt(i) 已经不是数字了
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    int tmp = stack.pop();
                    stack.push(num * tmp);
                } else if (preSign == '/') {
                    int tmp = stack.pop();
                    stack.push(tmp / num);
                }

                // num = 0;
                // 能将 num 放在整体 while 外面，while 循环 和 if 判断是相互独立
                // num = 0 不会将 num = num * 10 + (c - '0') 覆盖了
                // 但是这个位置不好，最好放到 while 循环之前，即开始下轮循环前，将 num 重置为 0

                if (i < s.length() && s.charAt(i) == ' ') i++;
                if (i < s.length()) preSign = s.charAt(i);
            }
        }
        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }
}
