package alg_02_train_dm._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 21:16
 * @Version 1.0
 */
public class _09_42_trapping_rain_water3 {

    // KeyPoint 方法三 双指针 => 最优解，性能非常好，打败 100 %
    // 双指针代替 leftMax 和 rightMax
    public int trap3(int[] height) {

        // KeyPoint 常见优化
        // 数组 => 变量
        // 计算当前柱子能装的多少水时，只是使用了一次 leftMax[i], rightMax[i]
        // 故考虑使用两个指针 leftMax，rightMax，从而将数组优化掉，节省空间

        int n = height.length;
        if (n <= 2) return 0;

        // left 之前最大值 => 不包括 left
        // right 之后最大值 => 不包括 right
        int leftMax = 0, rightMax = 0;

        // 对撞指针 left 和 right
        // 注意：left 必须从 0 开始，right 必须从 n - 1 开始
        // 原因：第一根柱子或者最后一根柱子有可能是最大值
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            // 每轮 while 循环，更新 leftMax 和 rightMax 变量值
            // 因为不知道是 left 还是 right 指针移动，故两个都要更新
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // KeyPoint 核心：装水量取决于短板，木桶效应
            // 那一侧低，结算那一侧，因为高的一侧，必然能接住水

            // 若 left 柱子低，则结算 left 柱雨水
            if (height[left] < height[right]) {
                // 只有 leftMax > height[left]，说明左则有高墙，可以接住水
                res += leftMax - height[left];
                left++;
            } else {
                // 只有 rightMax > height[right]，说明右则有高墙，可以接住水
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
