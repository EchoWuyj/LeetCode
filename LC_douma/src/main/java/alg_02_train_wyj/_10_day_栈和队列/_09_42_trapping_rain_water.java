package alg_02_train_wyj._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 13:09
 * @Version 1.0
 */
public class _09_42_trapping_rain_water {
    public int trap1(int[] height) {
        if (height.length <= 2) return 0;
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int maxHeight = Math.min(leftMax, rightMax);
            if (maxHeight > height[i]) {
                res += maxHeight - height[i];
            }
        }
        return res;
    }
}
