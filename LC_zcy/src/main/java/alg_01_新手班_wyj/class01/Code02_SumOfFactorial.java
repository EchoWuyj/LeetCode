package alg_01_新手班_wyj.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-09-06 18:59
 * @Version 1.0
 */
public class Code02_SumOfFactorial {
    public static void main(String[] args) {
        System.out.println(f1(4));
        System.out.println(f2(4));
    }

    public static int f1(int N) {
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res += fact(i);
        }
        return res;
    }

    public static int fact(int num) {
        int res = 1;
        for (int i = 1; i <= num; i++) {
            res *= i;
        }
        return res;
    }

    public static int f2(int N) {
        int res = 0;
        int temp = 1;
        for (int i = 1; i <= N; i++) {
            temp *= i;
            res += temp;
        }
        return res;
    }
}
