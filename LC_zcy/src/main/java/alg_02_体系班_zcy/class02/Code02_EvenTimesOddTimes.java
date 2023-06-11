package alg_02_体系班_zcy.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 0:28
 * @Version 1.0
 */
public class Code02_EvenTimesOddTimes {

    // 题目二:
    // 一个数组中有一种数出现了奇数次,其他数都出现了偶数次,怎么找到并打印这种奇数次的数
    public static void printOddTimesNum1(int[] arr) {
        // 异或eor
        int eor = 0;
        // 将所有数都异或起来,最后的结果就是出现奇数次的数,偶数个数异或的结果为0
        // 本质:利用异或性质
        //   0^N=N,N^N=0
        //   异或运算满足交换律和结合率
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 题目三(常用技巧,以后经常使用到)
    // 怎么把一个int类型的数,提取出最右侧的1来:N&((~N)+1)
    public static int getMostRight(int N) {

        // 前面多余的0都进行了省略
        // 假设输入的N:011011010000
        // 对应10进制 int N = 1744

        //   N   011011010000
        //  取反 100100101111
        //  加1  100100110000
        // ---------------------
        // ~N+1  100100110000

        //
        //   011011010000
        // & 100100110000
        // ---------------------
        //   000000010000 将最右侧的1提取出来了

        // 对外输出形式还是10进制数字,想要以2进制表示需要调用printByBinary()方法
        return N & ((~N) + 1);
    }

    // 按照2进制方式进行输出
    public static void printByBinary(int num) {
        // 为了保证高位在前,低位在后,所以需要从31降序遍历
        for (int i = 31; i >= 0; i--) {
            // 从最高位31到最低位0,开始和1进行按位&
            // 测试输出的print还是println一开始就想清楚
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    // 题目四(常见面试题)
    // 一个数组arr中,有两种数出现了奇数次,其他数都出现了偶数次,怎么找到并打印这两种数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        // 假设a和b是这两种数
        // 将所有数都进行异或,最终剩下a^b,即eor=a^b,同时eor!=0
        // 则必然有一位是1,作为区分0和1的基准点,这里不妨去eor最右侧的1,则对应下面的rightOne
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        // eor最右侧的1提取出来
        int rightOne = eor & (-eor);

        // 定义变量one为eor',用来存储a或者b
        //    遍历数组,提取出数组中&rightOne!=0的元素,表明这些元素对应位上为1
        //    将这些元素异或到one(eor')上,这样就只有a或者b被eor'抓到,因为偶数次元素异或的结果为0
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                // 最右侧的1,只是用来区分a或者b,但是^=过程arr[i]对应1的高位是保留下来的
                one ^= arr[i];
            }
        }
        // 一个是eor',另外一个就是eor^eor'
        // 可以将其中一个认为一个是a,另外一个是b
        // 如eor'=a,则eor'^eor=a^a^b=b;
        // 这里异或需要加上(),提高优先级,否则通过字符串凭借符号变成了String类型,String类型是不能异或的
        System.out.println(one + " " + (eor ^ one));
    }

    // 计算二进制1的个数
    public static int bit1counts(int N) {
        int count = 0;
        // 效率比for循环要高
        while (N != 0) {
            // 获取最右侧的1
            int rightOne = N & ((~N) + 1);
            count++;
            // 将最右侧的1抹掉,N的最右侧1位置保持不变
            // rightOne为N的最右侧1位,两个相与,结果为0
            // KeyPoint 不能使用&,1&1=1,同时这里也是需要=号的
            //  不能单单是N^rightOne,这样并没有将结果保存下来,所以代码报错
            N ^= rightOne;
            // N -= rightOne
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试提取出最右侧的1
        // 00000000000000000000000000010000
        printByBinary(getMostRight(1744));

        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);
    }
}
