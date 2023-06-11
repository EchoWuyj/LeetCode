package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-16 11:12
 * @Version 1.0
 */
public class _09_50_powx_n {
    public double myPow(double x, int n) {
        long y = n;
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }
        double res = 1;
        for (int i = 0; i < y; i++) {
            res *= x;
        }
        return res;
    }

    public double myPow1(double x, int n) {
        long y = n;
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }
        return quickPow(x, y);
    }

    public double quickPow(double x, long n) {
        if (n == 1) return x;
        if (n == 0) return 1;
        long mid = n / 2;
        double y = quickPow(x, mid);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow2(double x, int n) {
        long y = n;
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }

        double res = 1;
        while (y != 0) {
            if ((1 & y) == 1) res *= x;
            x *= x;
            y >>= 1;
        }
        return res;
    }
}
