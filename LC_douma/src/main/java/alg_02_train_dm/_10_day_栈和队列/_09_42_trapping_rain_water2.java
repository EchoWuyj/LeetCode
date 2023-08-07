package alg_02_train_dm._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 21:16
 * @Version 1.0
 */
public class _09_42_trapping_rain_water2 {

    // KeyPoint 方法二 数据预计算
    // 将 leftMax 和 rightMax for 循环提前，避免 for 循环的叠加
    // 时间复杂度 O(n)
    public int trap2(int[] height) {

        // KeyPoint 常见优化
        // 在 for 循环中，遍历过程求最值
        // 数据预处理，将 for 循环提前抽取出来，不要 for 循环的叠加，导致时间复杂度很高

        int n = height.length;
        if (n <= 2) return 0;

        // leftMax 表示每个柱子左边最大值
        //   array = [1, 2, 1, 3, 4, 2, 5, 3]
        // leftMax = [1, 1, 2, 2, 3, 4, 4, 5]
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            // 计算第 i 根柱子的前面所有柱子的最大值
            // 当前 i，从左往右遍历，，遍历到 i-1，不包括当前 i 根柱子
            // => i-1 位置的比较结果 决定 i 位置的值
            // => 前面决定后面

            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        // rightMax 表示每个柱子右边最大值
        //   array  = [1, 2, 1, 3, 4, 2, 5, 3]
        // rightMax = [5, 5, 5, 5, 5, 5, 3, 3]
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 计算第 i 根柱子的后面所有柱子的最大值
            // 当前 i，从右往左遍历，遍历到 i+1，不包括当前 i 根柱子
            // => i+1 位置的比较结果 决定 i 位置的值
            // => 后面决定前面
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            // 当前这个柱子，装的水的单位数等于：min(leftMax, rightMax) - height[i]
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            if (minHeight > height[i])
                res += minHeight - height[i];
        }
        return res;
    }
}
