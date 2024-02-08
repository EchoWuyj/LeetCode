package alg_03_high_frequency._01_codetop.top_100;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 20:21
 * @Version 1.0
 */
public class _27_300_lengthOfLIS_补充 {

    // 最长递增子数组
    // 给定一个无序的整数数组，找到其中最长上升子数组的长度
    // 子数组连续
    // 动态规划
    public static int LengthOfLISA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最长递增子数组的长度
        int[] dp = new int[n];
        // 初始化为 1，表示此时单独一个元素长度
        Arrays.fill(dp, 1);

        int max = 1;
        // 子数组连续，故只需要一轮循环即可
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // 子数组递增，增子数组长度 +1
                // dp[i-1] 严格依赖于 dp[i]
                dp[i] = 1 + dp[i - 1];
                max = Math.max(max, dp[i]);
            } else {
                // else 可以不用设置 dp[i] = 1，将其省略，因为 dp[i] 初始值赋值就是 1
                dp[i] = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1};
        System.out.println(LengthOfLISA(arr));  // 2
    }
}
