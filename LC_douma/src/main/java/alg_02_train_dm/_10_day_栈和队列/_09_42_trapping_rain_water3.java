package alg_02_train_dm._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 21:16
 * @Version 1.0
 */
public class _09_42_trapping_rain_water3 {

    // KeyPoint 方法三 双指针代替 leftMax 和 rightMax
    // 常见优化
    // 计算当前柱子能装的多少水时，只是使用了一次 leftMax[i], rightMax[i]
    // 故考虑使用两个指针 leftMax，rightMax，从而将数组优化掉，节省空间
    public int trap3(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        // left 之前最大值 => 不包括 left
        // right 之后最大值 => 不包括 right
        int leftMax = 0, rightMax = 0;
        // 注意：left 必须从 0 开始，right 必须从 n - 1 开始
        // 原因：第一根柱子或者最后一根柱子有可能是最大值
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // left 柱子低，则结算 left 柱雨水，装水量取决于短板
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
