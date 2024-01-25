package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-18 15:17
 * @Version 1.0
 */
public class _92_227_calculate_01 {

    // 基本计算器
    // 数字加减括号：(1+(4+5+2)-3)+(6+8)
    public int calculate(String s) {
        // stack 存储 res 和 preSign 都是整数，所以使用 Integer
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preSign = 1;
        int num = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '-') {
                res += preSign * num;
                preSign = -1;
                num = 0;
            } else if (c == '+') {
                res += preSign * num;
                preSign = 1;
                num = 0;
            } else if (c == '(') {
                stack.push(res);
                stack.push(preSign);
                preSign = 1;
                res = 0;
            } else if (c == ')') {
                res += preSign * num;
                num = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }
        return res + preSign * num;
    }
}
