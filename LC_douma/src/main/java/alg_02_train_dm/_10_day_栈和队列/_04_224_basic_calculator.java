package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 15:35
 * @Version 1.0
 */
public class _04_224_basic_calculator {

     /*
        leetcode 224 号算法题：基本计算器
        给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

        输入：s = "1 + 1"
        输出：2

        输入：s = " 2-1 + 2 "
        输出：3

        输入：s = "(1+(4+5+2)-3)+(6+8)"
        输出：23

        1 <= s.length <= 3 * 10^5
        s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
        s 表示一个有效的表达式
     */

    // 简化问题 => 没有括号的情况
    public static int calculate1(String s) {
        // 前置符号，1 表示正数，-1表示负数
        // 默认设置为 1，表示为正数
        int preSign = 1;
        int num = 0;
        int res = 0;

        // 13+1-3+42
        // 13
        // + => res += (preSign * num)
        //   => 下一轮 +1 符号，preSign = 1
        // 1
        // - => res += res += (preSign * num)
        // ...
        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
                // 每次遇到运算符 +/-，都将之前数值累加 res
                // 同时，通过 c 判断的'+'，'-'，确定 preSign，便于下轮计算
            } else if (c == '+') {
                res += (preSign * num);
                preSign = 1;
                // 已经处理过 num，将其清零，避免影响后续 num
                num = 0;
                // 若最开始'-'，先调整符号，即设置preSign，而不是执行 if (c <= '9' && c >= '0')
            } else if (c == '-') {
                res += (preSign * num);
                preSign = -1;
                num = 0;
            } // 若是空格，则不处理
        }
        return res + preSign * num;
    }

    // test
    public static void main(String[] args) {
        String str = "-13+1-3+42";
        System.out.println(calculate1(str)); // 53
    }

    public int calculate(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preSign = 1;
        int num = 0;
        int res = 0;
        // "17-13+(1-3)+42"
        // "17-13+(1-(2+3)+3)+42"
        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += (preSign * num);
                preSign = 1;

                num = 0;
            } else if (c == '-') {
                res += (preSign * num);
                preSign = -1;
                num = 0;
                // 没有遇到'('，则按照上面代码，即没有括号的逻辑进行运算
            } else if (c == '(') {
                // 注意：栈中元素顺序，先是结果集，再是符号，后面会用到
                stack.push(res);
                stack.push(preSign);
                // 左括号内，独立全新计算，故需要将 preSign 重置为 1，表示默认正数
                // 若 num 为 负数，则根据 '-'逻辑处理，符号不受之前的影响
                preSign = 1;
                res = 0;
            } else if (c == ')') {
                // 计算')'，将之前数值累加 res
                res += (preSign * num);
                // res 先乘以 preSign => stack.pop()
                // 表示 res 正负
                res *= stack.pop();
                // res 累和 之前 res => stack.pop()
                res += stack.pop();
                num = 0;
            }
        }
        // 最后一个数字
        return res + (preSign * num);
    }
}
