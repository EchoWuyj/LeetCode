package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 11:12
 * @Version 1.0
 */
public class _09_50_powx_n1 {
    public double myPow(double x, int n) {
        long ln = n;
        if (ln < 0) {
            x = 1 / x;
            ln = -ln;
        }

        double res = 1;
        for (int i = 0; i < ln; i++) {
            res *= x;
        }
        return res;
    }

    public double myPow1(double x, int n) {
        long ln = n;
        if (ln < 0) {
            x = 1 / x;
            ln = -ln;
        }
        return quickPow(x, ln);
    }

    public double quickPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        long mid = n / 2;
        double sub = quickPow(x, mid);

        return n % 2 == 1 ? sub * sub * x : sub * sub;
    }
}
