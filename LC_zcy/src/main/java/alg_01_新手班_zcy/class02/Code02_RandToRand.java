package alg_01_新手班_zcy.class02;

import static java.lang.Math.max;

/**
 * @Author Wuyj
 * @DateTime 2022-08-30 16:00
 * @Version 1.0
 */
public class Code02_RandToRand {
    public static void main(String[] args) {

        System.out.println("测试开始");

        // Math.random() -> 返回double数值 -> [0,1)
        // 在[0,1)上近似等概率随机返回一个数
        // 为什么是近似等概率?
        // 因为计算机小数有精度的,[0,1)小数不是无穷多的,是有穷尽的,则每个数是近似等概率的

        // 测试
        int testTimes = 1000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.3) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTimes); // 0.300467

        System.out.println("==============================");

        // [0,1) -> [0,8) 同样是等概率的
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes); // 0.625815
        System.out.println((double) 5 / (double) 8); // 0.625

        System.out.println("==============================");

        int K = 9;
        // Math.random() * K的范围[0,K) -> 向下取整[0,8]

        // 定义数组记录0-8每个数字出现的次数
        // counts[0] 表示0出现的次数
        int[] counts = new int[9];

        for (int i = 0; i < testTimes; i++) {
            // 向下取整之后,ans的范围[0,8]
            int ans = (int) (Math.random() * K);
            // 将ans当做数组的索引,出现一次则自增
            counts[ans]++;
        }

        for (int i = 0; i < K; i++) {
            // 每个数字出现的次数都是差不多的,可以认为每个元素出现的概率是等概的
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

        System.out.println("==============================");

        // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x调整成x平方
        count = 0;
        double x = 0.3;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTimes); //0.089893
        System.out.println(Math.pow(x, 2)); //0.09

        System.out.println("==============================");

        // 随机机制，只能用f1(1-5上的随机)，等概率返回0和1
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f2() == 0) {
                count++;
            }
        }
        // f2() == 0 的概率为 0.500318,接近理论的50%
        System.out.println((double) count / (double) testTimes);

        System.out.println("==============================");

        // 1~7等概率返回一个数字
        counts = new int[8];
        // 索引从1开始,将索引0排除在外
        for (int i = 1; i < testTimes; i++) {
            int num = g();
            counts[num]++;
        }

        for (int i = 1; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }

    //--------------------------------------------------------------

    // 返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x调整成x平方
    public static double xToXPower2() {

        // 1)只有保证max()中两个Math.random(), Math.random()都是<x,才有xToXPower2() < x
        //   又因为两次独立的概率相同都是(0,x),则总的概率为x^2

        // 2)不一定两个Math.random()概率都是x,其中p<x,p=x也是满足的
        //   将所有的情况都包括,则总的概率为x*x=x^2

        return max(Math.random(), Math.random());
    }

    //--------------------------------------------------------------

    // 定义好的方法,相当于lib里的，不能改！
    // 实现1-5的随机
    public static int f1() {
        // 得先实现0-5的随机,再在其基础上+1
        return (int) (Math.random() * 5) + 1;
    }

    // 随机机制，只能用f1，等概率返回0和1
    public static int f2() {

        int ans = 0;
//        int ans; // 可以定义不赋初值
        do {
            ans = f1();
            // do while中的while是针对确定下来的ans进行判断,
            // 不能再去调用f1(),比如 while(f1() ==3),相当于两次调用
        } while (ans == 3);
        // 1,2 返回 0
        // 4,5 返回 1
        return ans < 3 ? 0 : 1;
    }

    //--------------------------------------------------------------

    // 在0~1随机的基础上,拼接每一位,得到000 ~ 111
    // _ _ _ 三个二进制位拼接的结果,从而做到等概率0~7等概率返回一个
    public static int f3() {
        // 通过左移移动到高位去
        // 这里的+号可以理解成拼接操作
        //      0001 右移2位  0100
        //      0001 右移1位  0010
        //   +  0001 不右移   0001
        //     -------------------
        //                   0111

        // 通过括号人为表示优先级
        return (f2() << 2) + (f2() << 1) + f2();
    }

    // 0 ~ 6等概率返回一个数字
    public static int f4() {
        int ans = 0;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    // 1~7 等概率返回一个数字
    public static int g() {
        return f4() + 1;
    }





    // KeyPoint 题目2(题目1推广)
    //  从a-b随机到c-d随机(a,b,c,d代表抽象数字)

    // 解题思路
    // 1.由 a-b 随机得到 0，1 等概率函数
    //      即如果b-a+1 是奇数，前一半数字返回0，得到后一半数字返回1，
    //      得到中间的一个数则重新调，就得到了 0，1 等概率(偶数同理)
    // 2.要得到c-d 随机，可以先得到 0-（d-c）随机，然后再加c,就得到了0-d 随机
    // 3.要得到 0 -（d-c）随机 就要看需要几个二进制位了
    //      1个二进制位可以得到0-2随机
    //      2个二进制位可以得到0-3（2^0+2^1）随机
    //      3个二进制位可以得到0-7（2^0+2^1+2^2）随机，·····
    // 4.然后做do,while,多余的部分重新调，否则就返回，得到0-(d-c)随机
    // 5.加c ,得到c-d随机

    //-----------------------------

    // KeyPoint 题目3 01不等概率随机到01等概率随机

    // 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
    public static int x() {
        // 84%概率返回0,16%概率返回1
        // 抽象化: 0概率p;1概率1-p
        // 获取两次随机值
        //      00 11 重做
        //      01 10 保留
        //      两者不是等概率的 pp或者(1-p)(1-p)
        //      01 -> 返回0
        //      10 -> 返回1
        //      两者是等概率的为 p(1-p)
        return Math.random() < 0.84 ? 0 : 1;
    }

    // 01不等概率随机->等概率返回0和1,各50%
    public static int y() {
        // 最开始的赋值定义很有必要!
        // 保证返回的结果在0和1之间!
        int ans = 0;
        do {
            // 第一次在do里面赋值x()给ans,ans为0或1
            ans = x();
            // 第二次在while里面使用ans和x()进行比较,只有在两次不同才能跳出while循环
            // ans = 0 1
            // ans = 1 0
            // 跳出循环,此时概率是相同的p(1-p)
        } while (ans == x());

        return ans; // 返回结果必是0和1等概率
    }
}
