package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 19:50
 * @Version 1.0
 */

// 69. x 的平方根
public class _13_69_sqrtX {

    // KeyPoint 方法一 时间复杂度：O(x)
    // 0 <= x <= 2^31 - 1 => 超时
    public int mySqrt1(int x) {
        int ans = -1;
        for (int k = 0; k <= x; k++) {
            // √x = k，x = k^2，因为存在将 k 的小数抹去，故 x >= k^2
            // 找最大 k，使得 k^2 <= x 即可，k 从 0 到 x 依次进行判断
            // 强转 long，避免数据溢出
            if ((long) k * k <= x) {
                ans = k;
            }
        }
        return ans;
    }

    // KeyPoint 方法二 二分查找
    // 方法一是从 0 到 x 进行线性查找，且数列是严格升序的数组 => 二分查找
    // 时间复杂度：O(logx)
    public int mySqrt(int x) {
        // 遍历范围 [0,x] 的两端，就对应的 left 和 right
        int left = 0, right = x;
        int ans = -1;
        // 不断在循环体内找到 k
        while (left <= right) {
            int k = left + (right - left) / 2;
            if ((long) k * k <= x) {
                // 需要将 k 记录下来，因为求解的是 k 的最大值
                ans = k;
                // k 不一定是最大值，让 left 右移，看看 k 是否还能再增大
                left = k + 1;
            } else {
                right = k - 1;
            }
        }
        return ans;
    }
}
