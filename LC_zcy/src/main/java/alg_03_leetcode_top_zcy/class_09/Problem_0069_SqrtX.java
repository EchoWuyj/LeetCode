package alg_03_leetcode_top_zcy.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 13:27
 * @Version 1.0
 */
public class Problem_0069_SqrtX {

    /*
        给你一个非负整数x,计算并返回x的算术平方根
        由于返回类型是整数,结果只保留整数部分,小数部分将被舍去

        x=8,输出2
        8的算术平方根是2.82842...由于返回类型是整数,小数部分将被舍去(向下取整)

        104开方结果一定在1-104之间,使用二分(1+104)/2=52
        分析52*52和104的关系,52*52>104,则52~104都不可能是答案
        不断二分,直到二分至死
     */

    // x一定非负,输入可以保证
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 3) {
            return 1;
        }
        // 定义long,避免m*m溢出
        long ans = 1;
        long L = 1; // 范围1-x
        long R = x;
        long M = 0;

        // 二分最后一定能让L和R错过
        while (L <= R) {
            M = (L + R) / 2;
            if (M * M <= x) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        // long类型转int,得满足返回值要求
        return (int) ans;
    }
}
