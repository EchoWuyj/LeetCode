package alg_02_体系班_zcy.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 0:12
 * @Version 1.0
 */
public class Code01_Swap {
    public static void main(String[] args) {
    /*
        认识异或运算
            异或运算:相同为0,不同为1
            同或运算:相同以1,不同为0
            异或运算就记成无进位相加！ 1+0=1,1+1=0,0+0=0

        异或运算的性质
            1)0^N=N,N^N=0(从无进位相加理解)
              0+N=N,N+N=0
            2)异或运算满足交换律和结合率(异或运算和位置和顺序无关,只和1个数奇偶有关,位置交换顺序变换之后不影响1的个数)

        养成写算法时,自己自言自语又不让人烦,当面试官听到自己无意中说道最优解时,
        让面试官帮你一下,让你做出来,面试官很有成就感,而不是自己碾压面试官

     */

        //题目一:如何不用额外变量交换两个数

        // int a=甲
        // int b=乙

        // a=a^b  a=甲^乙(a已经不是原来的a了,而是a=甲^乙)
        // b=a^b  b=甲^乙^乙=甲
        // a=a^b  a=甲^乙^甲=乙

        int a = 16;
        int b = 603;

        System.out.println(a);// 16
        System.out.println(b);// 603

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);// 603
        System.out.println(b);// 16

        //--------------------------------

        int[] arr = {3, 1, 100};

        // i和j若是相同位置,指向同一片内存位置,则始终都是自己于自己异或,结果为0
        int i = 0;
        int j = 0;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + "-" + arr[j]); // 0-0

        System.out.println(arr[0]);// 0
        System.out.println(arr[2]);// 100

        //--------------------------------

        swap(arr, 0, 0);

        System.out.println(arr[0]);// 0
        System.out.println(arr[2]);// 100
    }

    // i和j是不同位置,需要保证内存不同,swap方法成立
    public static void swap(int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
