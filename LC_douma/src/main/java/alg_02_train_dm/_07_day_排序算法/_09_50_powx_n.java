package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 11:03
 * @Version 1.0
 */
public class _09_50_powx_n {

    /*
        50. Pow(x, n)
        实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）

        示例 1：
        输入：x = 2.00000, n = 10
        输出：1024.00000

        示例 2：
        输入：x = 2.10000, n = 3
        输出：9.26100

        示例 3：
        输入：x = 2.00000, n = -2
        输出：0.25000
        解释：2^-2 = 1/2^2 = 1/4 = 0.25

        提示：
        -100.0 < x < 100.0
        -2^31 <= n <= 2^31-1
        n 是一个整数
        -10^4 <= x^n <= 10^4

        KeyPoint 补充说明
        2^31 = 2147483648 => 21亿，而 1亿 = 1 * 10^8
        => 21 * 10^8 => O(n) 算法通过不了

        -2^31 <= n <= 2^31-1 => 记忆：左右范围都有 '-'，只是 '-' 位置不同

     */

    // KeyPoint 方法一 直接模拟 => 数学上一个一个相乘
    // O(n) -> 超时，需要加速
    public double myPow1(double x, int n) {

        // bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        // => 处理负数越界的常见手段，扩大数据类型成 long

        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        // 使用 double，否则;精度不够
        double res = 1;
        for (int i = 0; i < b; i++) {
            res *= x;
        }
        return res;
    }

    //  KeyPoint 方法二 快速幂 => 分治算法 + 递归
    // 时间：O(logn)
    // 空间：O(logn)
    public double myPow2(double x, int n) {

        // 比 O(n) 更好的算法，只能是 O(logn)，联想二分算法思想，每次只是算一半，即对 n 进行二分
        // x → x^2 → x^4 → x^8 → x^16 → x^32 → x^64
        // (x^2)^2 = x^(2*2) = x^4
        // 从 x 开始，每次直接把上一次的结果进行平方，计算 6 次就可以得到 x64 的值，而不需要对 x 乘 63 次 x
        // => 逆向思考，x^n -> x^(n/2) -> x^(n/4) -> 不断分治，直到最小子问题，达到递归边界，再在归的过程操作

        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        return quickPow(x, b);
    }

    private double quickPow(double x, long n) {

        if (n == 0) return 1.0;
        if (n == 1) return x;

        // n 为 偶数
        // 1,2,3,4,5,6
        //     ↑
        //  n/2 = 3

        // n 为 奇数
        // 1,2,3,4,5,6,7
        //     ↑
        // 7/2 =3;，左边 3 个，右边 3个，中间 4 独立

        // 递的规程
        // x^n -> x^(n/2) -> x^(n/4) -> x 或者 x^0
        long mid = n / 2;
        // y = x^(n/2)
        double y = quickPow(x, mid);

        // 归的过程：
        // 1.如果 n 为偶数，那么 x^n = y^2；
        // 2.如果 n 为奇数，那么 x^n = y^2 * x；
        return n % 2 == 0 ? y * y : x * y * y;
    }

    // KeyPoint 方法三 位运算
    // 时间：O(32) => O(1)
    // 空间：O(1)
    public double myPow(double x, int n) {
        // bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1;
        // 关键判断 b 是 2 的几次方
        while (b != 0) {
            // 编码    8   4    2    1
            // 二进制  2^3 2^2  2^1  2^0
            //   9    1   0    0    1
            // x^9 = x^8 * x^1
            if ((b & 1) == 1) res *= x;
            x *= x;
            // b 已经转正，可以直接使用有符号右移
            b >>= 1;
        }
        return res;
    }
}
