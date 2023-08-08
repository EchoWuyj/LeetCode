package alg_02_train_dm._10_day_栈和队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 17:03
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram1 {

    /*
        84. 柱状图中最大的矩形
        给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
        每个柱子彼此相邻，且宽度为 1 。
        求在该柱状图中，能够勾勒出来的矩形的最大面积。

        示例 1:
        输入：heights = [2,1,5,6,2,3]
        输出：10
        解释：最大的矩形为图中红色区域，面积为 10

        提示：
        1 <= heights.length <=10^5
        0 <= heights[i] <= 10^4
     */

    // KeyPoint 方法一 枚举宽 => 超时
    // 时间复杂度 O(n^2)
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int n = heights.length;

        // 求解面积：长*宽
        // 长：通过 [left, right] => 因为两端都包括，故需要 +1
        // 宽：遍历过程中取 minHeight

        // 固定 left
        for (int left = 0; left < n; left++) {
            int minHeight = heights[left];
            // 固定 right
            for (int right = left; right < n; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                // [left, right] => 因为两端都包括，故需要 +1
                int width = right - left + 1;
                res = Math.max(res, minHeight * width);
            }
        }
        return res;
    }

    // KeyPoint 方法二 枚举高 => 超时
    // 时间复杂度 O(n^2)
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int res = 0;
        // 将图中每个柱子固定成一个高度
        for (int mid = 0; mid < n; mid++) {
            // 计算面积，必须使用 height，从而实现固定高度
            // 在此基础上，再去找最长的宽度，从而求得最大面积
            int height = heights[mid];
            // 从 mid 位置左右延伸，确定左右边界
            int left = mid, right = mid;
            // 只有找到第一个小于 height 位置才停止，此时 left 和 right 在有效宽度之外
            while (left >= 0 && heights[left] >= height) left--;
            while (right < n && heights[right] >= height) right++;

            // 宽度：[right,left] 两端都不包括在内，故要减 1
            res = Math.max(res, height * (right - left - 1));
        }
        return res;
    }
}
