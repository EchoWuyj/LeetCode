package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:32
 * @Version 1.0
 */
public class _30_42_trap {
    public int trap(int[] height) {

        // 判空
        if (height == null || height.length <= 2) return 0;
        // 左右两侧最大值
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 结算低的一侧，高的一侧，保证水不会流出去
            if (height[left] < height[right]) {
                // 遍历的过程，将所有雨水都要接住
                res += (leftMax - height[left]);
                left++;
            } else {
                res += (rightMax - height[right]);
                right--;
            }
        }
        return res;
    }
}
