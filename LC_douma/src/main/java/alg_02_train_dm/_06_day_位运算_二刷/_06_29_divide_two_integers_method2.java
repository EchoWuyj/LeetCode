package alg_02_train_dm._06_day_位运算_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-05 16:28
 * @Version 1.0
 */
public class _06_29_divide_two_integers_method2 {

    // KeyPoint 方法一 另外一种形式：负数转正数可能存在越界问题，故正数转负数 => 超时
    // 时间复杂度：O(n)，n 是最大值 2147483647 -> 10^10
    // 补充：1 亿 100_000_000，一共 9 位数，后面有 8 个 0
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        // 因为将 -2147483648 转成正数会越界，但是将 2147483647 转成负数，则不会。
        // 故考虑将 a 和 b 都转成负数

        // 将正数转成负数，避免越界
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;

        // 因为已经处理越界，故可以将 while 循环条件，写成 a <= b(a-b <= 0)
        // 最好就写成 a-b <= 0 形式，这样肯定不存在越界情况
        while (a <= b) {
            a -= b;
            res++;
        }
        return sign == 1 ? res : -res;
    }
}
