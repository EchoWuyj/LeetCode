package alg_02_train_dm._06_day_位运算_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 16:06
 * @Version 1.0
 */
public class _06_29_divide_two_integers_method3 {

    // KeyPoint 方法二 每次尝试减去除数的倍数
    // 时间复杂度：O([logn]^2)
    // 其中 n 是最大值 2147483647 --> 10^10
    public int divide3(int a, int b) {

        // 每次一个数减，速度太慢了，时间复杂度很高
        // => 故每次尝试减去除数的倍数
        // 17 - 3 > 0    17 - (3 << 0) > 0
        // 17 - 6 > 0    17 - (3 << 1) > 0
        // 17 - 12 > 0   17 - (3 << 2) > 0   res += 1 << 2 = 4
        // 17 - 24 < 0   17 - (3 << 3) < 0

        // 后一位尝试数 是 前一位尝试数 一倍 => 从而减少判断次数
        // => 6 和 3
        // => 12 和 6
        // => 每次都是乘以 2，即每次都是左移 1 位

        // 相减的结果，继续进行下轮循环
        // 17 - (3 << 2) = 5
        // 5 - 3 > 0     5 - (3 << 0) > 0    res += 1 << 0 = 5
        // 5 - 6 < 0     5 - (3 << 1) < 0

        // 相减的结果，无法进行下轮循环
        // 5 - (3 << 0) = 2
        // 2 - 3 < 0

        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        if (a > 0) a = -a;
        if (b > 0) b = -b;

        int res = 0;
        // 时间复杂度 O([logn]^2)
        while (a <= b) {
            int tmp = b;
            int k = 1;

            // KeyPoint tmp << 1 存在数据溢出问题
            // 1.若 tmp = -2147483648，tmp << 1 导致最高 31 位的 1 溢出
            //   此时 tmp = 0，a = -2147483648 < tmp 恒成立，从而执行死循环
            // 2.为了避免数据溢出，需要保证 tmp >= -2^30，-2^31 / 2 = -2^30
            //   十进制 -2^30，其十六进制形式：0xc0000000
            // 3.-2^31 <= a <= tmp << 1 =>  -2^30 <= tmp
            //   比如：最大值 -10，tmp >= -5

            // KeyPoint 注意事项
            // 测试代码不要直接将代码删除，而是先复制，再其复制代码基础上进行修改
            while (tmp >= 0xc0000000 && a <= (tmp << 1)) {
                // tmp << 1 等价于 tmp * 2
                tmp <<= 1;

                // KeyPoint 优化 => 实在不理解，该行可以省略，几乎没有什么影响
                // 如果 k 已经大于最大值的一半的话，那么直接返回最小值
                // 因为此时 k <<= 1 肯定会大于等于 2147483648，这个超过了题目给的范围
//                if (k > Integer.MAX_VALUE / 2) {
//                    return Integer.MIN_VALUE;
//                }

                k <<= 1;
            }
            // 因为 tmp < 0，实际上 a + (-tmp)
            a -= tmp;
            res += k;
        }
        return sign == 1 ? res : -res;
    }

    // 若题目可以使用 long 长整型 => 没有限制 dividend 和 dividend 均为 32 位有符号整数 => 需要掌握
    // 避免数据溢出，则使用 long 数据类型，简化越界判断
    public int divide4(int a, int b) {

        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        long pa = Math.abs((long) a);
        long pb = Math.abs((long) b);
        int res = 0;

        while (pa - pb >= 0) {
            long tmp = pb;
            int k = 1;

            while (pa >= (tmp << 1)) {
                tmp <<= 1;
                k <<= 1;
            }
            pa -= tmp;
            res += k;
        }
        return sign == 1 ? res : -res;
    }
}
