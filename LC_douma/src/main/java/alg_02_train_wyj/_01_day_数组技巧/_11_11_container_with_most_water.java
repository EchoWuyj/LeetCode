package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 23:09
 * @Version 1.0
 */
public class _11_11_container_with_most_water {
    public int maxArea(int[] height) {
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public int maxArea1(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(area, res);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
