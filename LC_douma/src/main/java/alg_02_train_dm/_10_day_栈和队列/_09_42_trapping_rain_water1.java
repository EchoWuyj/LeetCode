package alg_02_train_dm._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 13:09
 * @Version 1.0
 */
public class _09_42_trapping_rain_water1 {
    /*
        42. 接雨水
        给定 n 个非负整数 表示每个宽度为 1 的柱子的高度图
        计算按此排列的柱子，下雨之后能接多少雨水。

        输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
        输出：6
        解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
        在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

        提示：
        n == height.length
        1 <= n <= 2 * 10^4
        0 <= height[i] <= 10^5

     */

    // KeyPoint 方法一 暴力
    // 思路
    // 1.计算每个柱子(每个index位置)能接的雨水数，再将雨水数累和
    // 2.每个柱子能接雨水数：找该柱子左右两侧最大高度中的较小值(木桶效应)，减去本身高度
    // 时间复杂度 O(n^2)
    public int trap1(int[] height) {
        if (height.length <= 2) return 0;
        int res = 0;
        int n = height.length;
        // 第一个柱子和最后一个柱子没法接雨水，故只需要从第二个柱子遍历到倒数第二个柱子即可
        for (int i = 1; i < n - 1; i++) {
            // 1.求左边的最大值 => 从 i-1 向左遍历
            // KeyPoint 在 for 循环中，遍历过程求最值 => 常见优化
            // 数据预处理，将 for 循环提前抽取出来，不要 for 循环的叠加，导致时间复杂度很高
            int leftMax = 0;
            // 最大值是包括第一个柱子和最后一个柱子的
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 2.求右边的最大值 => 从 i+1 向右遍历
            int rightMax = 0;
            // 最大值是包括第一个柱子和最后一个柱子的
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 3.当前这个柱子能装的水的单位数等于
            // => min(leftMax, rightMax) - height[i]
            // => 木桶效应
            int maxHeight = Math.min(leftMax, rightMax);
            // 确保 maxHeight > height[i]，否则 maxHeight - height[i] 为负数
            if (maxHeight > height[i])
                res += maxHeight - height[i];
        }
        return res;
    }
}
