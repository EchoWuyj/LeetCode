package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:32
 * @Version 1.0
 */
public class _30_42_trap {

    // 接雨水
    // 双指针
    public int trap(int[] height) {

        // 判空
        if (height == null || height.length <= 2) return 0;
        // 左右两侧最大值
        int leftMax = 0, rightMax = 0;
        // 左右指针
        int left = 0, right = height.length - 1;
        int res = 0;

        // left 必须严格小于 right
        // 若是 left 和 right 相等，则结束 while 循环
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 低的一侧 结算，因为低的一侧是短板，决定能接多少水
            // 高的一侧 不用结算，因为高的一侧能保证水不会流出去
            if (height[left] < height[right]) {
                // 遍历的过程，将所有雨水都要接住
                res += (leftMax - height[left]);
                left++;
            } else { // 相等结算那边都一样，等号放那一侧都无所谓
                res += (rightMax - height[right]);
                right--;
            }
        }
        return res;
    }
}
