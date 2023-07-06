package alg_02_train_dm._06_day_位运算_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-05 16:29
 * @Version 1.0
 */
public class _06_29_divide_two_integers_method4 {

    // KeyPoint 方法四  每次从最大位数开始尝试，31位、30位 => 需要掌握(背诵)
    // 时间复杂度：O(1)
    public int divide4(int a, int b) {

        // 17 - (3 << 31) < 0
        // 17 - (3 << 30) < 0
        // 17 - (3 << 29) < 0
        // 17 - (3 << 2) > 0   res += 1 << 2 = 4

        // 17 - (3 << 2) = 5

        // 5- (3 << 1) < 0
        // 5- (3 << 0) > 0 res += 1 << 0 = 5

        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        // 需要保证 a，b 都是正数，本质还是 a - b，需要保证 a，b 正负统一性
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;

        // 从 31 位开始一直尝试到 0 位
        for (int i = 31; i >= 0; i--) {

            // b << i <= a，其中 b << i 存在越界
            // 即'乘以'会越界，但是'除以'不存在越界，故将'乘以'改成'除以'

            // (a >>> i) - b >= 0，
            // 1.使用无符号右移 >>>，不用考虑符合位，从而消除 负号 -
            //   Integer.MIN_VALUE >>> 1 得 1073741824

            // 2.不要改变原始顺序：(a >>> i) >= b
            //   这个也是为了 若 b = -2147483648，则 (a >>> i) >= b 永远为 true
            //   但 (a >>> i) - b >= 0 为 false

            if ((a >>> i) - b >= 0) {
                a -= (b << i);
                res += (1 << i);
            }
        }
        return sign == 1 ? res : -res;
    }

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        // 最大值 左移 1 位 => 由正变负：2147483647 => -2
        System.out.println(max << 1); // -2

        // 最小值 左移 1 位 => 由负变零：-2147483638 => 0
        int min = Integer.MIN_VALUE;
        System.out.println(min << 1); // 0

        //  最小值 无符号右移 1 位 -2147483638 => 1073741824
        System.out.println(min >>> 1); // 1073741824
    }
}
