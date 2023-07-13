package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-07-12 9:59
 * @Version 1.0
 */
public class _09_50_powx_n2 {
    public double myPow(double x, int n) {
        long ln = n;
        if (ln < 0) {
            x = 1 / x;
            ln = -ln;
        }
        double res = 1;
        while (ln != 0) {
            if ((ln & 1) == 1) res *= x;
            x *= x;
            ln >>= 1;
        }
        return res;
    }
}
