package alg_02_train_dm._01_day_数组技巧_二刷._02_技巧二_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:39
 * @Version 1.0
 */
public class _11_11_container_with_most_water {
    /*
        11. 盛最多水的容器(非常高频题)
        给定一个长度为 n 的整数数组 height
        有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        返回容器可以储存的最大水量。

        说明：你不能倾斜容器

        输入：[1,8,6,2,5,4,8,3,7]
        输出：49
        解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。
        在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

        输入：height = [1,1]
        输出：1

        提示
        n == height.length
        2 <= n <= 10^5
        0 <= height[i] <= 10^4

        KeyPoint 数据规模 与 时间复杂度
        2 <= n <= 10^5 => 小于 O(n^2)
     */

    // KeyPoint 方法一 暴力
    // 思路：固定一根柱子，计算后续柱子与该柱子能装的水，并在其中选择最大值
    // 时间复杂度 O(n^2)-> 超出时间限制

    public int maxArea1(int[] height) {
        int n = height.length;
        int res = 0;
        // '数组轴'和'坐标系轴'不是同一个轴
        for (int i = 0; i < n; i++) {
            // 从后面一个柱子计算
            for (int j = i + 1; j < n; j++) {
                // 高度受限于短侧
                int area = Math.min(height[i], height[j]) * (j - i);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    // 优化思路
    // 1.方法一中，有些计算是没有意义的
    // 2.优化需要找到暴力解的性能瓶颈点，结合题目场景特点，做具体的优化
    // => 在宽度一定的情况下，面积瓶颈取决于最短的那个边
    // => 在前一个柱子高度确定时，后一个柱子与其从左往右依次计算，不如直接在最优侧计算
    // KeyPoint 方法二 对撞指针
    // 对撞指针，O(n)
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, area);
            // 面积瓶颈取决于低的一侧，只有低的一侧往里移动，变成更高的柱子，面积才有可能增大
            // 故移动左边，还是移动右边 => 取决于左边和右边谁低，低的那侧向里移动，有可能变成高的柱子
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
