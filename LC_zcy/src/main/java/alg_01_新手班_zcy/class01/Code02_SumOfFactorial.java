package alg_01_新手班_zcy.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-08-29 21:18
 * @Version 1.0
 */
public class Code02_SumOfFactorial {

    // 给定一个参数N 返回：11+2！+3！+4！+…+N！的结果
    // 两种不同的方式实现,比较两种代码的好坏
    public static void main(String[] args) {
        System.out.println(f1(3));
        System.out.println(f2(3));
    }

    // 方式一
    public static long f1(int N) {
        long result = 0;
        for (int i = 1; i <= N; i++) {
            // factorial中参数是i,不是固定的N
            result += factorial(i);
        }

        return result;
    }

    public static long factorial(int N) {
        long result = 1;
        for (int i = 1; i <= N; i++) {
            result *= i;
        }
        return result;
    }

    // 方式二
    public static long f2(int N) {
        long res = 0;
        long cur = 1;
        // 注意i得从1开始,不能从0开始,因为其表示阶乘
        for (int i = 1; i <= N; i++) {
            cur *= i;
            // 定义res进行存储
            res += cur;
        }
        return res;
    }
}
