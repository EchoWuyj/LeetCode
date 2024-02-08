package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 13:34
 * @Version 1.0
 */
public class _71_470_rand10_补充{

    // KeyPoint 补充
    // 类似题目：从 1 ~ 5 随机到 1 ~ 7 随机

    // 定义好的方法,相当于lib里的，不能改！
    // 实现 1-5 的随机
    public static int f1() {
        // 得先实现0-5的随机,再在其基础上+1
        // random() 输出范围 [0.0,1.0)
        // random()*5 输出范围 [0.0,5.0)
        return (int) (Math.random() * 5) + 1;
    }

    // 随机机制，只能用 f1，等概率返回 0 和 1
    public static int f2() {
        int ans = 0;
        do {
            ans = f1();
            // do while中的while是针对确定下来的ans进行判断,
            // 不能再去调用f1(),比如 while(f1() ==3),相当于两次调用
        } while (ans == 3);
        // 1,2 返回 0
        // 4,5 返回 1
        return ans < 3 ? 0 : 1;
    }

    // 在0~1随机的基础上,拼接每一位,得到000 ~ 111
    //  _ _ _ 三个二进制位拼接的结果,从而做到等概率0~7等概率返回一个
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

    // 想要 1-7 则先获取 0-6，再去在基础上加1
    // 0 ~ 6 等概率返回一个数字
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
}
