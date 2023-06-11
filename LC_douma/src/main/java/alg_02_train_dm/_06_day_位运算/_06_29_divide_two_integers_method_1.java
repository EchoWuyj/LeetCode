package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 11:03
 * @Version 1.0
 */
public class _06_29_divide_two_integers_method_1 {
    /*
        29. 两数相除
        给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
        返回被除数 dividend 除以除数 divisor 得到的 商 。

        整数除法应该向零截断，也就是截去(truncate)其小数部分。
        例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。

        注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−2^31,  2^31 − 1] => 不能使用 long 类型，只能使用 int
        本题中，如果商 严格大于 2^31 − 1 ，则返回 2^31 − 1 ；如果商 严格小于 -2^31 ，则返回 -2^31 。

        提示
        -2^31 <= dividend, divisor <= 2^31 - 1
        divisor != 0

        示例 1:
        输入: dividend = 10, divisor = 3
        输出: 3
        解释: 10/3 = 3.33333.. ，向零截断后得到 3 。

        示例 2:
        输入: dividend = 7, divisor = -3
        输出: -2
        解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。

     */

    // KeyPoint 方法一 减法代替除法，每次尝试多减去一个除数
    // dividend 被除数
    // divisor 除数
    public int divide1(int a, int b) {

        // 乘法代替除法，本题不可以使用
        // 17 ÷ 3 = 5...2
        // 17 = 3 × 5 + 2

        // 减法代替除法，每次尝试多减去一个除数
        // 17 - 3 > 0
        // 17 - 3 - 3 > 0
        // 17 - 3 - 3 - 3 > 0
        // 17 - 3 - 3 - 3 - 3 > 0
        // 17 - 3 - 3 - 3 - 3 - 3 > 0 => 最多可以减去 5 个 3
        // 17 - 3 - 3 - 3 - 3 - 3 - 3 < 0

        // 32 位最大值：2^31 - 1 = 2147483647
        // 32 位最小值：-2^31 = -2147483648
        // -2147483648 / (-1) = 2147483648 > 2147483647 越界了
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        // 确定相处符号 => 被除数和除数异号，则结果负数，否则正数
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        // KeyPoint 若使用 long 类型，则不存在数据溢出
        // 数据溢出原因在于，Integer.MAX_VALUE = 2147483647，负数 -2147483648 变成 2147483648 > 2147483647 则数据溢出
        // 而使用 long 类型 Math.abs((long)-2147483648) = 2147483648，则不存数据溢出
//        long la = Math.abs((long) a);
//        long lb = Math.abs((long) b);

        // 通过 abs 方式取正，或者使用 -a 获取正数，结果都是其自身
        // 即使最小值 -2147483648 的绝对值还是 -2147483648 => Math.abs(-2147483648) = -2147483648
        // 但这不影响：最小值 - 正数 结果的正确性 => min = -2147483648，min - 4 = 2147483644

        // 可以不使用 long，使用 int 足够
        int la = Math.abs(a);
        int lb = Math.abs(b);

        int res = 0;
        // KeyPoint la - lb >= 0 和 la >= lb  while 循环条件并不等价
        // la = -2147483648
        // lb = 1
        // la - lb = 2147483647 => 利用 la - lb 形式，避开了负数转正数可能越界问题
        // la >= lb 循环条件不成立
        while (la - lb >= 0) {
            la -= lb;
            res++;
        }
        // KeyPoint bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    // 补充：测试 Integer.MIN_VALUE +/- 正数
    public static void main(String[] args) {
        int min = -2147483648; // Integer.MIN_VALUE

        // - min 还是 min
        System.out.println(-min);

        // 最小值 -2147483648 的绝对值还是 -2147483648
        System.out.println(Math.abs(min)); // -2147483648

        // min 为 -2147483648 => 不影响 min - 正数
        // 将 min 当做 2147483648 来计算
        System.out.println(min - 1); // 2147483647

        // min 为 -2147483648 => 不影响 min - 正数
        // 将 min 当做 2147483648 来计算
        System.out.println(min - 4); // 2147483644

        // min 为 -2147483648 => 不影响 min + 正数
        System.out.println(min + 4); // -2147483644
    }

    // KeyPoint 方法二 负数转正数可能存在越界问题，故正数转负数
    // 因为将 -2147483648 转成正数会越界，但是将 2147483647 转成负数，则不会。所以，我们将 a 和 b 都转成负数
    // 时间复杂度：O(n)，n 是最大值 2147483647 -> 10^10，补充：1 亿 100_000_000，一共 9 位数，后面有 8 个 0
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        // 将正数转成负数，避免越界
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        // 因为已经处理越界，故可以将 while 循环条件，写成 a <= b
        // a - b <= 0
        while (a <= b) {
            a -= b;
            res++;
        }
        return sign == 1 ? res : -res;
    }
}
