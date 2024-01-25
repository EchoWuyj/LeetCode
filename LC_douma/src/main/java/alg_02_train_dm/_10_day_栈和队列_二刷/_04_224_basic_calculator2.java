package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 13:11
 * @Version 1.0
 */
public class _04_224_basic_calculator2 {

    // 有括号
    public int calculate(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preSign = 1;
        int num = 0;
        int res = 0;

        // "17 - 13 + ( 1 - 3 ) + 42"
        // "17 - 13 + |(1- |( 2 + 3 ) + 3) + 42"

        // 代码逻辑
        // 1.遇到 '('，将 '(' 之前数值，符号(preSign)，压入进栈
        // 2.遇到 ')'，将 ')'之前累和值 和 栈顶元素累加，正负由 preSign 确定
        // 3.若没有遇到 '(' 或 ')'，则按照之前代码逻辑计算 => 即没有括号的逻辑进行运算
        // KeyPoint 本质：该类问题通用解 => 逆波兰式(后缀表达式)
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
                // KeyPoint 新增的两种情况 '(' 和 ')'
                // '(' 和 ')' 分别进行不同处理
            } else if (c == '(') {
                // 注意：栈中元素顺序，先是结果集，再是符号，后面会用到
                stack.push(res);
                stack.push(preSign);
                // 左括号内，独立全新计算，故需要将 preSign 重置为 1，表示默认正数
                // 若不将 preSign 重置为 1，则 preSign 有可能为 -1，从而影响结果的正确性
                preSign = 1;
                // res 已经存入栈中 =>  res 清零
                res = 0;
            } else if (c == ')') {
                // 遇到 ')'，触发计算，将之前数值累加 res
                // => 效果等价于 '+' 或 '-'，只是没有设置 preSign
                res += (preSign * num);
                // 清零 num
                num = 0;

                // 1.stack.pop() 先弹栈的是：preSign
                // res *= stack.pop() => 表示 res 正负;
                // 确定当前 res 的正负
                res *= stack.pop();
                // 2.stack.pop() 后弹栈的是：数值
                // 当前 res 累和 之前 res[stack.pop()]
                res += stack.pop();
            }
            // KeyPoint 空格跳过不处理
        }

        // 判断最后一个字符
        // 1.若最后一个字符是 ')'，preSign * num，其中 num = 0，累加不影响 res
        // 2.若最后一个字符不是 ')'，res 需要累加最后一个数字，再触发一下计算
        return res + (preSign * num);
    }
}
