package algorithm._14_bit_operator.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-04-01 13:31
 * @Version 1.0
 */
public class LeetCode_461_HammingDistance {

    // 方法一:异或,调库统计1的个数
    public int hammingDistance01(int x, int y) {
        // bitCount计算一个int,long类型的数值在二进制下"1"的数量
        //               而byte,short,char,int统一按照int方法计算
        return Integer.bitCount(x ^ y);

        // KeyPoint 复杂度分析
        //  核心代码 Integer.bitCount(x ^ y);
        //  时间复杂度：O(1) 主要有两个操作：异或操作，花费常数时间；
        //                  另外还用调用内置的bitCount方法。Java中的bitCount
        //                  只进行了有限次位移、位与和相加操作，所以时间复杂度为O(1)
        //  空间复杂度：O(1) 只使用常数空间保存结果。
    }

    // 方法二:自定义实现统计1的个数,逐位右移
    public int hammingDistance02(int x, int y) {

        // KeyPoint 注意事项
        //  注意:0≤x, y<231（保证了其值为整数,右移的过程不用考虑-1的问题）
        //  计算机底层的操作都是使用补码进行的,因为整数的原反补都一样,所以使用原码也是可以的

        // 得到异或结果
        int xor = x ^ y;
        // System.out.println(xor);

        //保存当前1的个数
        int count = 0;

        // 逐位右移,直到结果为0,因为结果为0,则里面不可能有1
        while (xor != 0) {
            // 和1进行&操作,来判断最后一位是否为1,如果最后一位为1,count++
            if ((xor & 1) == 1) count++;
            // 右移一位(因为是整数,右移补的是0,不会补-1,所以没有影响)
            // 注意是右移1位,并不是xor >>= xor
            xor >>= 1;
        }

        return count;

        // KeyPoint 复杂度分析
        //  核心代码： while (xor != 0)
        //  时间复杂度：O(1)。主要考察while循环的次数，循环的次数和n没有关系
        //             Java中int类型是固定的32位，所以最多需要32次位移判断。
        //  空间复杂度：O(1)，只用到了常数个临时变量。
    }

    // 方法三:快速位移
    public int hammingDistance03(int x, int y) {
        // 得到异或结果,注意不是&操作
        int xor = x ^ y;
        // 保存当前1的个数
        int count = 0;

        // 快速位移,每次寻找当前最右面的一个1,直接消去
        // 每循环一次,则消去一个1
        while (xor != 0) {
            // 同时将当前消去的结果还保存在xor中,关键看消去几次结果为0
            // &=先进行位与,再去将位与的结果赋值为xor
            xor &= (xor - 1);
            count++;
        }

        return count;

        // KeyPoint 复杂度分析
        //  核心代码：while (xor != 0)
        //  时间复杂度：与方法二类似，主要考察while循环的次数。
        //              由于跳过了所有的0，所以循环执行的次数，就是1的个数count，最坏情况下为32。
        //              相比之下，所做的迭代操作更少了。
        //  空间复杂度：O(1)，只用到了常数个临时变量。
    }

    public static void main(String[] args) {
        LeetCode_461_HammingDistance hammingDistance = new LeetCode_461_HammingDistance();
        System.out.println(hammingDistance.hammingDistance02(1, 4));
    }
}

