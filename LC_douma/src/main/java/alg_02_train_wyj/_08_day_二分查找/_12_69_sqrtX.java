package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 19:27
 * @Version 1.0
 */
public class _12_69_sqrtX {

    public int mySqrt1(int x) {
        int ans = -1;
        for (int k = 0; k <= x; k++) {
            if ((long) k * k <= x) {
                ans = k;
            }
        }
        return ans;
    }

    public int mySqrt(int x) {
        int left = 0, right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
