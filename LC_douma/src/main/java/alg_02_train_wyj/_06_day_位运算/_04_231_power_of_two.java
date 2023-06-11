package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 23:03
 * @Version 1.0
 */
public class _04_231_power_of_two {
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        long x = n;
        return (x & (x - 1)) == 0;
    }

    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = n;
        return (x & -x) == x;
    }
}
