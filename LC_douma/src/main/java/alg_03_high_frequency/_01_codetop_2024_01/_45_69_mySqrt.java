package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 10:32
 * @Version 1.0
 */
public class _45_69_mySqrt {
    public int mySqrt(int x) {
        int left = 0, right = x;
        // res 初值设置为负数
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断条件 <= x 是不发生变化的，而不是 <= right
            if ((long) mid * mid <= x) {
                res = mid;
                // 满足条件，尝试将 left 继续增大
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
