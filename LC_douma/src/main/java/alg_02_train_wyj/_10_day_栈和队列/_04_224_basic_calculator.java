package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 15:50
 * @Version 1.0
 */
public class _04_224_basic_calculator {

    // 没有括号
    public static int calculate1(String s) {
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
                res += (preSign * num);
                preSign = -1;
                num = 0;
            }
        }
        return res + (preSign * num);
    }

    public static void main(String[] args) {
        String str = "13+1-3+42";
        System.out.println(calculate1(str)); // 53
    }

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
                res += (preSign * num);
                preSign = -1;
                num = 0;
            } else if (c == '(') {
                stack.push(res);
                stack.push(preSign);
                preSign = 1;
                res = 0;
            } else if (c == ')') {
                res += (preSign * num);
                res *= stack.pop();
                res += stack.pop();
                num = 0;
            }
        }
        return res + (preSign * num);
    }
}
