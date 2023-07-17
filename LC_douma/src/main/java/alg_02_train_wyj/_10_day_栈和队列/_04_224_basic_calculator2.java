package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 13:44
 * @Version 1.0
 */
public class _04_224_basic_calculator2 {
    public int calculate(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preSign = 1;
        int num = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += (preSign * num);
                preSign = 1;
                num = 0;
            } else if (c == '-') {
                res += preSign * num;
                preSign = -1;
                num = 0;
            } else if (c == '(') {
                stack.push(res);
                stack.push(preSign);
                preSign = 1;
                res = 0;
            } else if (c == ')') {
                res += (preSign * num);
                num = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }
        return res + preSign * num;
    }
}
