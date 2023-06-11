package algorithm._09_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-03-24 21:58
 * @Version 1.0
 */
public class Recursion {

    // 定义一个计算阶乘的方法
    public static int factorial(int n) {
        // 基准情形
        if (n == 0) return 1;
        return factorial(n - 1) * n;
        // 时间复杂度,从n开始,n-1,n-2...一直到0,即n为多大计算次数就是多少,故为O(n)的时间复杂度
        // 空间复杂度为O(n)
    }

    // 尾递归(需要编译器支持)
    //  递归函数最后一句的return返回,直接返回就是自身的调用,
    //  没有其他额外的操作,递归的结果直接拿来使用,因此外层不需要保存额外的栈空间相关的信息

    // 传入参数中不能简单只传入一个参数n,而是另外还需要一个保存当前计算结果的状态acc
    public static int factorial2(int n, int acc) {
        if (n == 0) return acc;
        // 将需要保存的临时变量factorial(n-1)*n当成了方法调用的一个参数,直接保存将其传入
        return factorial2(n - 1, acc * n);
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }
}
