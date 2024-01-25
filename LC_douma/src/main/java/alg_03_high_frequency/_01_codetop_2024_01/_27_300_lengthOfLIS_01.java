package alg_03_high_frequency._01_codetop_2024_01;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 20:21
 * @Version 1.0
 */
public class _27_300_lengthOfLIS_01 {

    // 最长递增子数组 => 子数组连续
    // 给定一个无序的整数数组，找到其中最长上升子数组的长度

    public static int LengthOfLISA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最长递增子数组的长度
        int[] dp = new int[n];
        // 初始化为 1，表示此时单独一个元素长度
        Arrays.fill(dp, 1);

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // 子数组递增，增子数组长度 +1
                dp[i] = 1 + dp[i - 1];
                maxLen = Math.max(maxLen, dp[i]);
            } else {
                // else 不用设置 dp[i] = 1，因为 dp[i] 初始值赋值就是 1
                dp[i] = 1;
            }

        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1};
        System.out.println(LengthOfLISA(arr));  // 2
    }
}
