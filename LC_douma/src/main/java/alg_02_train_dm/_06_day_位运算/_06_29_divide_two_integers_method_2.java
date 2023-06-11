package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 16:06
 * @Version 1.0
 */
public class _06_29_divide_two_integers_method_2 {

    // KeyPoint 方法三 每次尝试减去除数的倍数
    // 时间复杂度：O(logn * logn)，n 是最大值 2147483647 --> 10^10
    public int divide3(int a, int b) {

        // 每次尝试减去除数的倍数
        // 17 - 3 > 0    17 - (3 << 0) > 0
        // 17 - 6 > 0    17 - (3 << 1) > 0
        // 17 - 12 > 0   17 - (3 << 2) > 0   res += 1 << 2 = 4
        // 17 - 24 < 0   17 - (3 << 3) < 0
        // KeyPoint 注意：3 6 12 24 每次都是乘以 2，即每次都是左移 1 位

        // 17 - (3 << 2) = 5
        // 5 - 3 > 0     5 - (3 << 0) > 0    res += 1 << 0 = 5
        // 5 - 6 < 0     5 - (3 << 1) < 0

        // 5 - (3 << 0) = 2
        // 2 - 3 < 0
        // 时间复杂度 O(logn * logn)

        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a <= b) {
            int value = b;
            int k = 1;
            // value << 1 存在数据溢出问题
            // 解释：若 value = -2147483648，value << 1 导致最高 31 位的 1 溢出，此时 value = 0
            // a = -2147483648 < value 恒成立，从而执行死循环

            // 为了避免数据溢出，需要保证 value >= 0xc0000000，其是十进制 -2^30 的十六进制形式，-2^31 / 2 = -2^30
            // 比如：最大值 -10，value >= -5

            // KeyPoint -2^31 <= a <= value << 1 =>  -2^30 <= value

            // KeyPoint 测试代码，不要直接将代码删除，而是先复制，再其复制代码基础上进行修改
            while (value >= 0xc0000000 && a <= (value << 1)) {
                // value << 1 等价于 value * 2
                value <<= 1;

                // 代码优化：
                // 如果 k 已经大于最大值的一半的话，那么直接返回最小值
                // 因为此时 k <<= 1 肯定会大于等于 2147483648，这个超过了题目给的范围
                // KeyPoint 实在不理解，该行可以省略，几乎没有什么影响
                if (k > Integer.MAX_VALUE / 2) {
                    return Integer.MIN_VALUE;
                }

                k <<= 1;
            }
            // value < 0，实际上 a + (-value)
            a -= value;
            res += k;
        }
        return sign == 1 ? res : -res;
    }

    // KeyPoint 方法四  每次从最大位数开始尝试，31位、30位
    // 时间复杂度：O(1)
    public int divide4(int a, int b) {
        // 17 - (3 << 31) < 0
        // 17 - (3 << 30) < 0
        // 17 - (3 << 29) < 0
        // 17 - (3 << 2) > 0    res += 1 << 2 = 4
        // 17 - (3 << 2) = 5
        // 5- (3 << 1) < 0
        // 5- (3 << 0) > 0 res += 1 << 0 = 5

        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        // 需要保证 a，b 都是正数，本质还是 a - b，需要保证 a，b 正负统一性
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;
        // 从 31 位开始一直尝试到 0 位
        for (int i = 31; i >= 0; i--) {

            // b << i <= a '乘以'存在越界问题
            // '乘以'会越界，但是'除以'不存在越界，故将'乘以'改成'除以'
            // 使用无符号右移，为了将 -2147483648 看成 2147483648

            // 注意，这里不能是 (a >>> i) >= b 而应该是 (a >>> i) - b >= 0
            // 这个也是为了避免 b = -2147483648，如果 b = -2147483648
            // 那么 (a >>> i) >= b 永远为 true，但是 (a >>> i) - b >= 0 为 false

            if ((a >>> i) - b >= 0) {
                a -= (b << i);

                // KeyPoint 代码优化：这里控制 res 大于等于 INT_MAX
                // 实在不理解，该行可以省略，几乎没有什么影响
                if (res > Integer.MAX_VALUE - (1 << i)) {
                    return Integer.MIN_VALUE;
                }

                res += (1 << i);
            }
        }
        return sign == 1 ? res : -res;
    }
}
