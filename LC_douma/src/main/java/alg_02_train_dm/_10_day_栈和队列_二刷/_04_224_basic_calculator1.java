package alg_02_train_dm._10_day_栈和队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 15:35
 * @Version 1.0
 */
public class _04_224_basic_calculator1 {

     /*
        224 号算法题：基本计算器
        给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

        输入：s = "1 + 1"
        输出：2

        输入：s = " 2-1 + 2 "
        输出：3

        输入：s = "(1+(4+5+2)-3)+(6+8)"
        输出：23

        提示：
        1 <= s.length <= 3* 105
        s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 => 注意 ' ' 表示空格
        s 表示一个有效的表达式

        '+' 不能用作一元运算(例如， "+1"和 "+(2 + 3)"无效)
        '-' 可以用作一元运算(即 "-1"和 "-(2 + 3)"是有效的)
        输入中不存在两个连续的操作符
        每个数字和运行的计算将适合于一个有符号的 32位 整数

     */

    // KeyPoint 简化问题 => 没有括号的情况 => 直接模拟
    public static int calculate1(String s) {

        // 数字都是有符号，需要定义变量，表示符号
        // 前置符号，1 表示正数，-1表示负数 => 默认设置为 1，表示为正数
        int preSign = 1;

        // 数字
        int num = 0;
        // 结果集
        int res = 0;

        // 代码逻辑
        // 1.每次遇到运算符 '+' 或 '-'，都是 res 累加上一轮 preSign * num
        // 2.同时，通过 c 判断 '+' 或 '-'，确定 preSign，便于下轮计算

        // 13 + 1 - 3 + 42

        // 13，默认 preSign = 1
        // + => 计算上一轮 => res += (1 * 13)
        //   => '+ ' => 下一轮符号，preSign = 1
        // 1
        // - => 计算上一轮 => res += (1 * 3)
        //   => '-' => 下一轮符号，preSign = -1
        // 3
        // + => res += (-1 * 3)
        //   => '+ ' => 下一轮符号，preSign = 1
        // ......

        for (char c : s.toCharArray()) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                res += (preSign * num);
                // 注意：每遇到 '+' 或 '-'，都要设置 preSign，避免收前几轮的影响
                // preSign 设置成和 '+' 对应的 1
                // => 保证后续遇到 '+' 或 '-' 时，保证正负正确 preSign * num，再去累加到 res 中
                preSign = 1;
                // 已经处理过 num，将其清零，避免影响后续 num
                num = 0;
            } else if (c == '-') {
                res += (preSign * num);
                // preSign 设置成和 '-' 对应的 -1
                // => 保证后续遇到 '+' 或 '-' 时，保证正负正确 preSign * num，再去累加到 res 中
                // => 若第一个 c 为'-'，先调整符号， preSign = -1
                preSign = -1;
                num = 0;
            } // 若是空格，则不处理
        }

        // for 循环结束，最后部分是数字，不是 '+' 或 '-'
        // '+' 或 '-'触发的是上一轮计算，本轮计算没有触发，故需要自己手动累加最后一个数字
        return res + preSign * num;
    }

    // test
    public static void main(String[] args) {
        // KeyPoint
        // 该代码针对首位为负值的情况同样适用
        // 因为最开始第一个字符 c ='-'，从而将 preSign 设置为 -1
        String str = "-13+1-3+42";
        System.out.println(calculate1(str)); // 27
    }
}
