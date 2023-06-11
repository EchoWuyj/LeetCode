package alg_02_train_dm._01_day_数组技巧._02_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:39
 * @Version 1.0
 */
public class _11_11_container_with_most_water {
    /*
        11. 盛最多水的容器(非常高频题)
        给定一个长度为 n 的整数数组 height 。
        有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        返回容器可以储存的最大水量。

        说明：你不能倾斜容器
    
        提示
        n == height.length
        2 <= n <= 10^5 => 小于 O(n^2)
        0 <= height[i] <= 10^4

        输入：[1,8,6,2,5,4,8,3,7]
        输出：49
        解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。
        在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

        输入：height = [1,1]
        输出：1

     */

    // KeyPoint 方法一 暴力 O(n^2)-> 超出时间限制
    //  优化：需要找到暴力解法的性能瓶颈点，结合题目场景特点，做具体的优化
    public int maxArea1(int[] height) {
        int n = height.length;
        int ans = 0;
        // '数组轴'和'坐标系轴'不是同一个轴
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 高度受限于短侧
                int area = Math.min(height[i], height[j]) * (j - i);
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    // 在宽度一定的情况下，面积瓶颈取决于最短的那个边
    // 对撞指针，O(n)
    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            // 移动左边，还是移动右边，取决于左边和右边谁低，低的那侧向里移动
            // 因为面积瓶颈取决于低的一侧，只有低的一侧往里移动，变成更高的柱子，面积才有可能增大
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
