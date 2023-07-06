package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 11:47
 * @Version 1.0
 */
public class _06_29_divide_two_integers1 {
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        int res = 0;

        int la = Math.abs(a);
        int lb = Math.abs(b);

        while (la - lb >= 0) {
            la -= lb;
            res++;
        }
        return sign == 1 ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483647, 1));
    }

    public int divide1(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;

        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a - b <= 0) {
            a -= b;
            res++;
        }
        return sign == 1 ? res : -res;
    }
}


