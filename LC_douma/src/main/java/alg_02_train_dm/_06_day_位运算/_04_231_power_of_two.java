package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 21:52
 * @Version 1.0
 */
public class _04_231_power_of_two {

    /*
        231. 2 的幂
        给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
        如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。

        示例 1：
        输入：n = 1
        输出：true
        解释：2^0 = 1

        示例 2：
        输入：n = 16
        输出：true
        解释：2^4 = 16

        提示：-2^31 <= n <= 2^31 - 1
         */

    // KeyPoint 方法一 数学方法 => 不断除以 2
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) return false;
        // 在能被 2 整除情况下，不断 n / 2，再去判断最后 n 是否为 1
        while (n % 2 == 0) n = n / 2;
        return n == 1;
    }

    // KeyPoint 方法二 根据二进制规则 => 位运算 => 移除最后一个 1
    // 若是 2 的幂，则二进制中只有一个1，故只要判断二进制中只有一个 1 即可
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        // KeyPoint 数据溢出 => 使用 long 类型接受
        //  1 当 n = -2147483648 的时候，n - 1 就会溢出
        //  2 int 取值范围 -2^31——2^31-1，即 -2147483648 ~ 2147483647
        long x = n;
        // 去掉最后一位 1 => n & (n-1)，移除 1 之后判断是否为 0
        return (x & (x - 1)) == 0;
    }

    // KeyPoint 方法二 根据二进制规则 => 位运算 => 拿到最后一个 1
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        // 当 n = -2147483648 的时候，-n 就会溢出
        // n = -2147483648，则 -n = 2147483648，还是存在数据溢出，故使用 long 存储
        long x = n;
        // 获取最后一个 1，考虑 2的次幂只有一个 1，故判断结果是否为 (x & -x) = x
        return (x & -x) == x;
    }
}
