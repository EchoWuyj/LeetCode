package alg_02_train_wyj._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 13:06
 * @Version 1.0
 */
public class _09_42_trapping_rain_water3 {
    public int trap3(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = n - 1;
        int res = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (height[left] < height[right]) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
