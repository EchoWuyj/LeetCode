package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-12 9:59
 * @Version 1.0
 */
public class _09_50_powx_n2 {

    // KeyPoint 方法三 位运算
    // 时间：O(32) => O(1)
    // 空间：O(1)
    public double myPow(double x, int n) {
        // KeyPoint bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        long ln = n;
        if (ln < 0) {
            x = 1 / x;
            ln = -ln;
        }
        double res = 1;
        // x^ln，将 ln 使用二进制表示，关键判断 ln 是 2 的几次方
        while (ln != 0) {

            // 编码     8   4    2    1
            // 二进制   2^3 2^2  2^1  2^0
            // ln = 9   1   0    0    1
            //  x^9 =  x^8 * x^1
            // 获取 ln 的最低位，若该位是 1，将其累乘到 res 中
            // 同时，x 和 ln 右移同步变化，即 ln 最低位表示在编码 2 位上，则 x 已经变成 x^2，再去累成到 res 中
            // 数字 1 的二进制，只有 1 一位，即 0000 ... 1
            if ((ln & 1) == 1) res *= x;
            x *= x;
            // ln 已经转正，可以直接使用有符号右移
            ln >>= 1;
        }
        return res;
    }
}
