package alg_02_train_wyj._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 11:58
 * @Version 1.0
 */
public class _09_42_trapping_rain_water2 {
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
        }
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            if (minHeight > height[i]) {
                res += (minHeight - height[i]);
            }
        }
        return res;
    }
}
