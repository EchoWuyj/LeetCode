package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _92_227_calculate_02 {

    // 基本计算器 II
    // 加减乘除，没有括号
    public int calculate(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int i = 0;
        while (i < n) {
            if (s.charAt(i) != ' ') {

                // 确定 num
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }

                // preSign 操作
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

                // 移动空格
                while (i < n && s.charAt(i) == ' ') i++;

                // 确定 preSign
                if (i < n) preSign = s.charAt(i);
            }
            i++;
        }
        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }
}
