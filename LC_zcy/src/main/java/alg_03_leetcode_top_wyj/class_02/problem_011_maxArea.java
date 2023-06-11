package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 16:56
 * @Version 1.0
 */
public class problem_011_maxArea {
    public int maxArea(int[] height) {
        if (height == null || height.length == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
