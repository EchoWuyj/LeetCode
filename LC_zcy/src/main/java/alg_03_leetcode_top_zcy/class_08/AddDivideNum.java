package alg_03_leetcode_top_zcy.class_08;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 12:50
 * @Version 1.0
 */
public class AddDivideNum {
    /*
        题目描述
	 	100 = 3 + 69258 / 714
	 	100 = 82 + 3546 / 197
	 	等号右边的部分,可以写成p1+p2/p3的形式
	 	要求p1和p2和p3,所使用的数字,必须把1~9使用完全,并且不重复
	 	满足的话,我们就说,形如p1+p2/p3,一个有效的"带分数"形式
	 	要求,p2/p3必须整除

	 	输入N,返回N有多少种带分数形式
	 	如:100,有11种带分数形式,输入N,N<10^8

	 	KeyPoint 本质:通过数据量来反向推理需要什么样时间复杂度算法

	 	9个数全排列
	    从9个数字中选择一个数字作为第一个位置,有9种选择；
	    从剩下的8个数字中选择一个数字作为第二个位置,有8种选择
	    ...
	    9x8x7x6x5x4x3x2x1=362880=9!

	    本题等价于:在9!个数(所有的枚举可能数)中什么位置补+或者/,
	              同时使用map保存,key:计算结果值,value:该计算结果值出现的次数(每种相同结果值次数+1)
	    如:362741598=>36+2741/598

	    硬解思路:
        分析给定一个数,需要多少步骤能求出解?
        即362741598随意添加+或者/,其可能性有多少种?

	    3+62741598,除号位置不是随意放,需要优化,保证X/Y有意义(保证整除),则X位数大于等于Y
	      6274/1598
	      62741/598
	      627415/98
	      6274159/8
	      一共有4种情况
	    36+2741598,
	    362+741598,
	    ...
	    一共是:4+3+3+2+2+1+1=16次

        一个数有16种可能,而总的枚举数量是 362880 => 362880*16 = 5806080 < 10^8
        所以硬解本题是可以通过的,可以不同找其他的尝试方法
	    KeyPoint 总结:使用已经有的数据量来猜方法(技巧)

	    这题比较好在于:数据量5*10^6,已经快到10^7次方了,关键在常数优化
                     代码写的不好有可能超时,代码写的不好就可能超时

        代码优化:尽量避免字符串"362745981"分割后"362|745|981",
                 再去转成int进行+或者/运算,这样常数项偏高,效率较低
        推荐做法:代码中一直使用int存储,对数字362745981进行分隔
                 362 = 362|745981 / 1000,000  => 745981有几位,则直接除以1几个0
                 745981 = 362|745981 % 1000,000
                 745|981 同样的方法处理,这样的方式处理速度要快于String转int

	    ---------------------------------------------------------

        补充:
        1000 = 1*10^3 将1后面几个0就当做几次方
        6203 取后面3位(取过之后将其划去),10^3,整体在6*10^3左右
        5,806,080 => 5*10^6 < 10^8
        KeyPoint 以后一个较大数字,按照3位一划分,这样比较好分析该数的数量级

        ---------------------------------------------------------

        补充:
        function bFun(n) {
             for(let i = 0; i < n; i++) { // 需要执行 (n + 1) 次
                 console.log("Hello, World!"); // 需要执行 n 次
             }
             return 0;       // 需要执行 1 次

            那么这个方法需要执行(n+1+n+1) = 2n +2
         }

        KeyPoint 关于时间复杂度,for循环部分代码,只需要看for循环即可,里面的代码(基本动作则)可以不用看
             因为for循环里面代码执行次数的数量级和for循环中i变量是一样的,因此只是系数上累加,而时间复杂度
             是忽略系数的,因此可以忽略

     */

    // 全局公共变量map
    // 1-9所有带分数形成的结果都算一遍放到map里去
    // key 数值:100
    // value 出现次数:11
    public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    // 通过数组定义10的数量级
    // arr[3]=1000,即arr[num]=10^num
    public final static int[] arr = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public static int ways(int n) {
        // 第一次计算
        if (map.size() == 0) {
            // 只有在第一次时会计算全部结果,若后面再查则直接从map获取即可
            // index=8,则表示第8位还没有指定
            process(123456789, 8);
        }
        // 若是没有结果值为N,则返回0个带分数
        return map.containsKey(n) ? map.get(n) : 0;
    }

    /**
     * @param num   当前数形成的样子,123456789
     * @param index 该哪个位置的数字去指定了(枚举所有可能)
     */
    public static void process(int num, int index) {
        // KeyPoint 返回值为void,最后在map中进行操作,不需要返回值

        // 初始数字123456789,index=8
        //  1 2 3 4 5 6 7 8 9  数字
        //  8 7 6 5 4 3 2 1 0  第几位
        // 将第8位数字去指定,第8位数字可以指定1,2...9
        //     1 ...
        //     2 ...
        //     ...
        //     9 ...
        // index=-1,递归边界,process结束,此时0-8位上的数字都已经确定
        // 此时添加+和/来进行计算可能的所有情况
        if (index == -1) {
            // KeyPoint num中8位~0位上的数字固定了,现在只要在num中添加+ /
            //      +分隔指定位置
            //      +分隔最右到2位置,保证p2和p3有数值
            for (int add = 8; add >= 2; add--) {
                int p1 = num / arr[add];
                int rest = num % arr[add];
                // num = 823|457916
                // 位数  876 543210
                // p1=823=>num/10^6=arr[add]
                // rest=457916=num%10^6=arr[add]

                // KeyPoint /分隔指定位置
                //      /分隔得尽量在靠右位置,能保证p2>p3,这样p2/p3是有意义的
                //      但是最右到1位置,保证p3有数值
                //      8位置,后面有8个数,从一半开始,即8/2
                for (int dev = (add >> 1); dev >= 1; dev--) {
                    int p2 = rest / arr[dev];
                    int p3 = rest % arr[dev];
                    // rest=3249|158
                    // 3249=rest/10^3=arr[3]
                    // 158=rest%10^3
                    // p2和p3得是整除

                    // KeyPoint a % b == 0 表示整数a可以被整数b整除,即a是b的倍数
                    if (p2 % p3 == 0) {
                        int ans = p1 + (p2 / p3);
                        // 判断是否在map中
                        if (!map.containsKey(ans)) {
                            map.put(ans, 1);
                        } else {
                            map.put(ans, map.get(ans) + 1);
                        }
                    }
                }
            }
        } else {
            // 当前在index位置,需要去指定index位置的数
            // 从index出发,每个swap都和index数进行交换,从高位到低位
            // KeyPoint swap必须从index开始,而不能从index-1开始,否则遗漏高位为1情况
            //      因为123456789,高位8和高位8交换,123456789不变,相当于高位8确定为1,再去指定高位7
            for (int swap = index; swap >= 0; swap--) {

                // 递归函数并没有将temp集合作为形参传入,因此每次调用递归函数都是新new一个ArrayList
                // ArrayList<Integer> temp = new ArrayList<>();

                int next = swap(num, index, swap);
                // index数已经指定好了,去下个位置继续指定
                // System.out.println(next);
                process(next, index - 1);

                 /*
                    递归函数分析:
                    1-pro index=2
                    2-pro index=1
                    3-pro index=0
                    递归结束:只有执行整个函数代码后,也就是到最后一个'}',
                            才表示递归完成返回上一层,一定要明确!

                    按照123进行递归,按照次序输出的结果
                    123
                    123
                    123
                    132
                    132
                    213
                    213
                    213
                    231
                    231
                    321
                    321
                    321
                    312
                    312
                 */
            }
        }
    }

    // KeyPoint swap功能:将num的某位L和某位R数字进行交换
    // 例如:
    //  1 2 3 4 5 6 7 8 9  数字
    //  8 7 6 5 4 3 2 1 0  第几位
    // 将num的第8位和第3位数字进行交换
    //  6 2 3 4 5 1 7 8 9
    public static int swap(int num, int L, int R) {
        // KeyPoint 获取num第L位(高位)和第R位数字(低位)
        // 3 4 5 6 7 1 8 9 2  数字
        // 8 7 6 5 4 3 2 1 0  第几位
        // 获取5位数字,对应数字是6
        // 345671892/10^5=3456%10=6 => 想要抹去后面x位,则相应除10^x即可,再通过%10,将最后一位弄出来
        // L第5位,对应arr[L]=10^5
        int bitL = (num / arr[L]) % 10;
        int bitR = (num / arr[R]) % 10;
        // 8 5 6 7 2 3 1 9 4
        // 8 7 6 5 4 3 2 1 0  第2位(R)和第6位(L)交换
        // 8 5 1 7 2 3 6 9 4
        // KeyPoint 本质:高位减,低位加
        //      L是高位,R是低位
        //      L/R位置的数大小没有关系
        return num - (bitL - bitR) * arr[L] + (bitL - bitR) * arr[R];
    }

    public static void main(String[] args) {
        // process(123, 2);

        int N = 100;
        long start;
        long end;
        // 第一次跑要去生成map,需要100多毫秒,
        // 但是只需要在第一次生成,以后都是直接查询的
        start = System.currentTimeMillis();
        System.out.println(N + "用带分数表示的方法数 : " + ways(N));
        end = System.currentTimeMillis();
        System.out.println("运行了(毫秒) : " + (end - start));
        // 第二次跑map已经在上面生成好了,自然很快
        N = 10000;
        start = System.currentTimeMillis();
        System.out.println(N + "用带分数表示的方法数 : " + ways(N));
        end = System.currentTimeMillis();
        System.out.println("运行了(毫秒) : " + (end - start));
    }
}
