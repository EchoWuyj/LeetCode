package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 19:27
 * @Version 1.0
 */
public class _12_69_sqrtX {

    public static int mySqrt1(int x) {
        int res = -1;
        for (int k = 0; k <= x; k++) {
            if ((long)k * k <= x) {
                res = k;
            }
        }
        return res;
    }

    public static void main(String[] args) {
       // System.out.println((long) 2147395599 * 2147395599);
    }

    public int mySqrt(int x) {
        return -1;
    }
}
