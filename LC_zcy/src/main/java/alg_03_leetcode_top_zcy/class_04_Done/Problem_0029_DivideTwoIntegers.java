package alg_03_leetcode_top_zcy.class_04_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 17:18
 * @Version 1.0
 */

// 两数相除
// Code02_BitAddMinusMultiDiv
public class Problem_0029_DivideTwoIntegers {

    // 原题要求:给你两个整数,被除数dividend和除数divisor,将两数相除要求:不使用乘法,除法和取余运算

    // KeyPoint 这里使用位运算实现加减乘除(整数,不支持小数)

    public static int add(int a, int b) {

        // 原始数值a+b等价于:二进制形式的a+b无进位相加,再加上进位信息
        // a+b=a'+b'=a"+b",直到b"=0,则a"为最终的结果

        // 一开始需要将sum赋值为a,因为若while循环不执行,则表示b=0此时,此时sum就为a,直接返回
        int sum = a;

        // KeyPoint 注意事项
        //  因为代码中不能出现加号,所以得while循环;直到进位信息为0,则无进位相加就是相加的结果
        while (b != 0) { // b是提供的形参

            // ^(异或运算)等价无进位相加信息
            //   01101
            // ^ 10110
            //  -------
            //   11011
            // 正好等价于去掉进位信息的两数相加
            sum = a ^ b;  // a'

            // (a&b)<<1等价于a+b的进位信息,所有进位信息都保留下来了
            //  1) a&b只有两个对应位置都是1,&的结果才是1
            //  2) 同时再左移1位,则得到的是,a和b的进位信息
            //          01101
            //        & 10110
            //        ---------
            //          00100
            // 左移1位
            //        ---------
            //          01000   注意:左移的过程在末尾添加0
            b = (a & b) << 1;  // b'

            // sum赋值为无进位相加信息,又通过赋值a=sum,则由a+b转化成a'+b'
            a = sum;
        }
        return sum;
    }

    // 相反数:取反(~)+1(先取反,再加1)
    // -Integer.MIN_VALUE == Integer.MIN_VALUE
    public static int negNum(int n) {
        // KeyPoint 使用函数add替换+,不能使用+
        return add(~n, 1);
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // KeyPoint 支持正负整数
    public static int multi(int a, int b) {

        // 乘法计算逻辑需要好好理解下
        // 10进制乘法
        //    76
        //   *23
        //  ------
        //   228
        //  152
        //  ------
        //  1748

        // 二进制
        //      0101(5)  a
        //      0110(6)  b
        //  ---------------
        //      0000        *0则为0
        //     0101|0       *1则为自身
        //    0101|00       同时移位后4个二进制数后面再补上0
        //   0000|000
        //  ---------------
        //   0011110(30)

        // 定义res,用来存储中间结果
        int res = 0;
        // b最后为0,说明1的位置已经被右移没有了,则直接跳出循环,返回ans
        while (b != 0) {
            // (b&1)!=0,则说明b的最后一位为1,将a加入到res中
            if ((b & 1) != 0) {
                res = add(res, a);
            }

            // KeyPoint 操作对称:一左移,一右移

            // 左移1位,末尾补0
            // 为了模仿乘法中,移位后4个二进制数后面再补上0
            a <<= 1;

            // 有符号>>,高位使用符号补充
            // 无符号右移1位,低位右移1位,高位补0
            // 使用>>>避免因为b是负数,导致其最高位为1,右移高位一直补1,则while没有结束时候
            b >>>= 1;
        }
        return res;
    }

    // 判断是否为负数
    public static boolean isNeg(int n) {
        return n < 0;
    }

    public static int div(int a, int b) {

        // 计算逻辑:
        //  先计算|a|和|b|的结果,再去根据ab的符号,对结果进行加工,但是系统最小值(Integer.MIN_VALUE)没有办法转绝对值,
        //  则该方法行不通,因为系统最小值的绝对值比系统最大值的绝对值多一个.比如-10~9,故以无法将系统最小值转成系统最大值

        //  a  0110
        //  b  0010
        //  b' 0100  将b左移移动到最接近a的位置b'
        //  ---------
        //     0100  该位一定有1

        //  a  0110  使用a减b,得到a'
        // -b' 0100
        //-----------
        //  a' 0010  得到a'

        // a' 0010
        // b  0010  此时b已经最接近a',所以b是不用移动的
        // ----------
        //    0010 该位一定有1
        // 将0100和0010累和的结果就是最终结果

        // KeyPoint 正式逻辑开始之前需将数值转成正数,负数不支持的
        //      减号- 不能使用
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;

        int res = 0;
        // x/y
        // KeyPoint 除不尽,则向下取整9/4=2,在二进制中体现为有个小尾巴舍弃了
        // x是非负的,x的最高位为0,所以跳过了最高位,直接从30位开始递减循环
        // KeyPoint for循环中不能使用--
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            // 这里优化了,使用x右移,让y能达到自己,避免y中的1位置,左移动到符号位,导致由正数变负数,从而导致错误
            // 因为y在移动中,存在移过1位之后,才发现自己x值大的情况,这样存在风险,而x右移则不存该风险
            // x从右移30位开始,所以x的值是慢慢变大的,找到恰好比y大的值
            if ((x >> i) >= y) {
                // 将相应位置上置为1(|或运算,有1则为1)
                res |= (1 << i);
                // 使用x减去左移后的y
                x = minus(x, y << i);
            }
        }

        // 如果符号不一致,则返回负值
        // 不等!=可以直接使用异或代替
        return (isNeg(a) ^ isNeg(b)) ? negNum(res) : res;
    }

    // 一般意义上a/b,可以考虑正负号
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;

            // a不是系统最小,但是b是系统最小
            // 一个数除以系统最小值结果为0
            // 3/(-2147483648),先算3/(2147483648)结果值非常小,可以认为无限接近0的,加上负号结果还是为0
        } else if (b == Integer.MIN_VALUE) {
            return 0;
            // a是系统最小,但是b不是系统最小
            // 假设-10是系统最小,9位系统最大
            // -10/2,-10是没法转10/2,因为系统最大没有10
        } else if (a == Integer.MIN_VALUE) {
            //  规定:如果除法结果溢出,则返回2^31−1
            //  系统最小/-1,此时为2^31,系统溢出,规定为系统最大
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                // 系统最小没法转成取绝对值变成正数,因为系统最大没有值与之对应
                // 需要通过下面的方式绕过系统最小值转成正数

                //   (a+1)/b=c
                //   a-(b*c)=d 补偿值
                //   d/b=e
                //   c+e=res

                //  -6/2
                // (-6+1)/2=-2
                // -6-(-2*2)=-2
                // -2/2=-1
                // -2+(-1)=-3

                int c = div(add(a, 1), b);
                int d = minus(a, multi(b, c));
                return add(c, div(d, b));
            }
        } else {
            // ab都不是系统最小,调用div方法
            return div(a, b);
        }
    }
}
