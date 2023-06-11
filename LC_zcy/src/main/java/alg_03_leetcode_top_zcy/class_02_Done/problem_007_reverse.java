package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-13 20:43
 * @Version 1.0
 */

// 整数反转
public class problem_007_reverse {
    public int reverse(int x) {
        /*
        给你一个32位的有符号整数x,返回将x中的数字部分反转后的结果
        如果反转后整数超过32位的有符号整数的范围[−2^(31),2^(31)−1],就返回 0
         */

        // 判断最高位的正负:32为1则为负,neg为true
        boolean neg = ((x >>> 31) & 1) == 1;

        // KeyPoint 将正数都转成负数来处理
        // 因为负数表达的绝对值域比正数表达的绝对值域要多一个,因此负数不是都可以转成正数处理,
        // 所以在运算中都是使用负数的(正数转成负数),最后再决定符号的
        x = neg ? x : -x;

        // 目的:检测溢出
        // 通过/10和%10这两部分值来描述系统最小

        // 系统最小/10
        int m = Integer.MIN_VALUE / 10;
        // 系统最小取余10
        int o = Integer.MIN_VALUE % 10;

        int res = 0;

        while (x != 0) {

            // 避免数据溢出
            // res < m,此时再*10(负数越乘越小),比系统最小还要小,则会溢出
            // res 没有溢出,但是余数溢出

            // KeyPoint 注意点
            //  1) 多重逻辑判断加上()来明确关系
            //  2) if判断中不是x,而是res,因为使用x是考虑不够全面,有可能x没有溢出,
            //     但是经过res = res * 10 + x % 10;才溢出
            if (res < m || (res == m && x % 10 < o)) {
                return 0;
            }
            // 13->31
            // 13 % 10 = 3 => 个位数*10变成十位数
            // 13 / 10 = 1 => 十位数变成个位数
            //
            res = res * 10 + x % 10;
            // 降一数位
            x /= 10;

            // -3 -2 -1 0 1 2 3
            // 负轴上距离0越远,则数值越小;
            // 负轴上距离0越近,则数值越大;
            // Integer.MIN_VALUE = -2147483648 足够小的
        }

        // 整个代码中都是负数进行运算的,所以最后需要还原
        return neg ? res : Math.abs(res);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE); // -2147483648
        System.out.println(Integer.MIN_VALUE / 10); // -214748364
        System.out.println(Integer.MIN_VALUE % 10); // -8
    }
}
