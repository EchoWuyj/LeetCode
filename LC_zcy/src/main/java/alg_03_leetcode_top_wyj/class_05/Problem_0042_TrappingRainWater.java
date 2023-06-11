package alg_03_leetcode_top_wyj.class_05;

/**
 * @Author Wuyj
 * @DateTime 2023-02-19 13:04
 * @Version 1.0
 */
public class Problem_0042_TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;
        int l = 1;
        int leftMax = height[0];
        int r = n - 2;
        int rightMax = height[n - 1];
        int water = 0;

        while (l <= r) {
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - height[l]);
                leftMax = Math.max(leftMax, height[l++]);
            } else {
                water += Math.max(0, rightMax - height[r]);
                rightMax = Math.max(rightMax, height[r--]);
            }
        }
        return water;
    }
}
