package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 19:50
 * @Version 1.0
 */
public class _05_227_basic_calculator_ii1 {

    /*
       227 号算法题：基本计算器二
       给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
       整数除法仅保留整数部分。

       输入：s = "3+2*2"
       输出： 7

       输入：s = " 3+5/2 " => 存在空格，需要处理
       输出： 5

       提示：
       1 <= s.length <= 3 * 10^5
       s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
       s 表示一个有效的表达式
       表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
       题目数据保证答案是一个 32-bit 整数

    */
    // 思路
    // 乘法和除法优先级高于加法和减法
    // 1.第一遍：将先乘法和除法计算，而对于加法和减法先不处理
    // 2.第二遍：将第一遍结果累加

    // KeyPoint 1.while 循环实现
    public static int calculate(String s) {

        // 记录每个数字前面符号，默认'+'
        // 不需要使用 Character，直接使用 char即可
        char preSign = '+';

        // 计算乘除运算前，先将加减运算对应数字暂存，第二轮再处理
        // => 先暂存，后使用 => 栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // i 遍历 s 的指针
        // 注意：for 循环和 while 循环的转换代码
        int i = 0;
        while (i < s.length()) {
            // 通过 if 判断，开头跳过空格，处理非空格字符
            // KeyPoint 思维误区
            // 注意：这里只能确保 i 位置字符不是空格，并不能保证后续字符中没有空格
            if (s.charAt(i) != ' ') {
                // num 定义while 循环之外，后面代码能使用
                // 新的一轮循环，num 重置为 0
                int num = 0;
                // KeyPoint 1.获取 num
                // Character.isDigit 用于判断 0-9 数字字符 => 熟悉这种 API
                // KeyPoint 易错点
                // 注意：这里是 while 循环，不是 if 循环，可能存在多个数字
                // 以后遇到类似的场景，一定得小心，不要相同的犯错
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                // KeyPoint 2.确定 num 前运算符
                // "3+21-2*2+5/2"，因为默认是'+'，即 "+3|+21|-2|*2|+5|/2"
                if (preSign == '+') {
                    // 遇到 '+'，只是将数字压栈，不进行运算
                    // stack：3 21
                    stack.push(num);
                } else if (preSign == '-') {
                    // 遇到 '-'，只是将数字压栈，不进行运算
                    // stack：3 21 -2
                    stack.push(-num);
                } else if (preSign == '*') {
                    // 获取栈顶元素 -2
                    int tmp = stack.pop();
                    // 遇到 '*' ，计算，并压栈
                    // 计算：-2 * 2 = -4
                    // stack：3 21 -4
                    stack.push(tmp * num);
                } else if (preSign == '/') {
                    // 获取栈顶元素 5
                    int tmp = stack.pop();
                    // 遇到 '/' ，计算，并压栈
                    // 计算：5 / 2 = 2
                    // stack：3 21 -4 2
                    stack.push(tmp / num);
                }

                // if ... else if 分支，只会执行其中一个，
                // 判断一个运算符号，将相应运算结果压栈后，数字和运算符之间可能存在空格
                // 故需要处理空格，将其跳过
                // 比如：" +3 + 5 / 2 "

                // KeyPoint 3.跳过空格
                // 目的是拿到下一个符号字符
                while (i < s.length() && s.charAt(i) == ' ') i++;

                // KeyPoint 4.获取 num 之后的运算符
                // i 跳出 while 循环，i 已经指向运算符号位置了， 故这里直接获取即可
                if (i < s.length()) preSign = s.charAt(i);
            }
            // KeyPoint 下一轮循环，i++，不要遗漏
            i++;
        }

        int res = 0;
        // 将第一遍结果累加
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+21-2*2+5/2"));
    }
}
