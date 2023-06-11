package algorithm._14_bit_operator.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-03-31 20:25
 * @Version 1.0
 */
public class LeetCode_231_PowerOfTwo {

    // 方法一:数学方法,连续除以2判断余数
    public boolean isPowerOfTwo01(int n) {
        // KeyPoint 边界情况的判断
        // 处理特殊情况:2的整次幂必须是正整数
        // KeyPoint 需要考虑到0的情况
        //  凡是判断的边界条件很容易出错,需要小心
        if (n <= 0) return false;

        // 不停地除以2,其结果再去%2,如果余数为0,表示该数还是偶数,则一直除以2,直到结果为奇数
        // 偶数的条件 n % 2 == 0
        while (n % 2 == 0) {
            n /= 2;
        }

        // 如果最终n变成了1,就是2的整次幂
        return n == 1;

        // KeyPoint 复杂度分析
        //  核心代码 while (n % 2 == 0)
        //  时间复杂度 2^n=N,n=log2(N)
        //  空间复杂度 没有使用额外空间,故空间复杂度O(1)
    }

    // 方法二:位运算,和自身减一做位与
    public boolean isPowerOfTwo02(int n) {
        if (n <= 0) return false;
        //   n   1000 0000
        // & n-1 0111 1111 -> 消去1
        // ----------------
        //       0000 0000

        //   n   1010 0010
        // & n-1 1010 0001
        // ----------------
        //       1010 0000

        // KeyPoint &运算需要加上扩号,涉及运算的优先级
        return (n & n - 1) == 0;

        // KeyPoint 复杂度分析
        //  核心代码  (n & n - 1)
        //  时间复杂度：O(1) 只需要做一次位运算
        //  空间复杂度：O(1) 只要是没有使用额外的空间,空间复杂度都是O(1)
    }

    // 方法三:位运算,和相反数做位与
    public boolean isPowerOfTwo03(int n) {
        if (n <= 0) return false;
        // 按位与之后,判断1的位置是否和n中1所在的位置是否一致
        return (n & -n) == n;

        //      7        0000 0111
        // &   -7=(~7+1) 1111 1001  -> ~7不考虑符号位,全部按位取反
        // -----------------------
        //               0000 0001  -> 保留最右边的1

        // KeyPoint 复杂度分析
        //  核心代码  (n & -n) == n
        //  时间复杂度：O(1) 只需要做一次位运算
        //  空间复杂度：O(1) 只要是没有使用额外的空间,空间复杂度都是O(1)
    }

    public static void main(String[] args) {
        LeetCode_231_PowerOfTwo powerOfTwo = new LeetCode_231_PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo01(218));
        System.out.println(powerOfTwo.isPowerOfTwo01(128));
    }
}
