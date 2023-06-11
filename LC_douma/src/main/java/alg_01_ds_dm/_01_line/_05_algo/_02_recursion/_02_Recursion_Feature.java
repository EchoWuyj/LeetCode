package alg_01_ds_dm._01_line._05_algo._02_recursion;

/**
 * @Author Wuyj
 * @DateTime 2023-03-07 10:49
 * @Version 1.0
 */
public class _02_Recursion_Feature {

    // 递归特点
    // 1 大问题 => 拆解子问题 ; 子问题解决 => 大问题解决
    // 2 子问题和大问题解决方法逻辑是一样的 <=> 递推公式
    // 3 一定存在递归终止条件(最小子问题的解已知)

    // 递归基本步骤
    // 1 确定递归函数的参数和返回值
    // 2 确定递归终止条件
    // 3 确定单层递归的逻辑 <=> 递推公式

    // a()死循环
    public static void a() {
        System.out.println("调用方法 a()");
        a();
        System.out.println("调用本身结束");
    }

    // a()加上递归边界
    public static void a(int times) {
        if (times == 0) return;
        System.out.println("前参数 times" + times);
        // times - 1 为下次递归形参
        a(times - 1);
        System.out.println("后参数 times" + times);
        // 整个递归函数执行完，返回递归出口
    }

    // 1到n之和
    public static int sum(int n) {
        // 递归边界
        if (n == 1) return 1;
        return n + sum(n - 1);
    }

    // 斐波那契数列
    public static int fibonacci(int n) {
        // 递归终止条件
        if (n == 1 || n == 2) return 1;
        // 如何解决子问题(具体步骤)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 走台阶：一次可以走 1 步或者 2 步
    public static int walkStair(int n) { // walkStair表示走n个台阶的走法
        // 1 递归终止条件
        if (n == 1 || n == 2) return n;
        // 2 递归公式:f(n)=f(n-1)+f(n-2)

        // 第一次选择走了1个台阶,剩下n-1台阶,则walkStair(n-1)
        // 第一次选择走了2个台阶,剩下n-2台阶,则walkStair(n-2)
        // => 两种可能相加为一共的走法
        return walkStair(n - 1) + walkStair(n - 2);
    }

    public static void main(String[] args) {
        a(2);
        System.out.println(sum(5));
    }
}
