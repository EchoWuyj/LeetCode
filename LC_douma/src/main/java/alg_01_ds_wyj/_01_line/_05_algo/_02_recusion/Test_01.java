package alg_01_ds_wyj._01_line._05_algo._02_recusion;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 12:43
 * @Version 1.0
 */
public class Test_01 {
    public static void a() {
        System.out.println("调用方法 a()");
        a();
        System.out.println("调用本身结束");
    }

    public static void a(int times) {
        if (times == 0) return;
        System.out.println("前参数 times" + times);
        a(times - 1);
        System.out.println("后参数 times" + times);
    }

    public static int sum(int n) {
        if (n == 1) return 1;
        return n + sum(n - 1);
    }

    public static int fibonacci(int n) {
        if (n == 1 || n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int walkStair(int n) {
        if (n == 1 || n ==2) return n;
        return walkStair(n - 1) + walkStair(n - 2);
    }

    public static void main(String[] args) {
        a(2);
        System.out.println(sum(5));
        System.out.println(fibonacci(7));

    }
}
