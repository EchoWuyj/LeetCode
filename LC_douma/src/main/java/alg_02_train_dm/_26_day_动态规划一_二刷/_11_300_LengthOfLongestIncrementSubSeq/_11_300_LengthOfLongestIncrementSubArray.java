package alg_02_train_dm._26_day_动态规划一_二刷._11_300_LengthOfLongestIncrementSubSeq;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 18:43
 * @Version 1.0
 */
public class _11_300_LengthOfLongestIncrementSubArray {

    // 最长递增子数组(子数组连续)
    // 给定一个无序的整数数组，找到其中最长上升子数组的长度

    // 1 3 5 0 8
    // 输出 3
    // 解释：
    // 最长递增 子数组 为 [1,3,5]
    // 最长递增 子序列 为 [1,3,5,8]

    // 类比：最大子数组之和
    // dp[i][j] 表示子数组 [i, j] 的最大子数组和
    // 将二维降成一维
    // => 区间 [i,j] 中最大数组之和在 [0,j] 中
    // => dp[i] 表示以索引为 i 的元素结尾的最大子数组和

    // 类似的状态定义
    // dp[i] 表示以索引为 i 的元素结尾的最长递增子数组的长度

    // 索引   0 1 2  3  4
    // nums: -2 1 -3 4 -1
    // dp     1 2 1  2 1

    // 状态转移方程：
    // nums[i] > nums[i-1](递增：严格大于)：dp[i]= 1 + dp[i-1]
    // nums[i] <= nums[i-1]：dp[i]= 1

    // 分析 dp[i]，一定需要和之前的状态联系起来，如：dp[i]= 1 + dp[i-1]
    // 这样计算 dp[i] 时，才是通过已知状态推导未知状态

    public static int LengthOfLISA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最长递增子数组的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // 子数组递增，增子数组长度 +1
                dp[i] = 1 + dp[i - 1];
                maxLen = Math.max(maxLen, dp[i]);
            }
            // else 不用设置 dp[i] = 1，因为 dp[i] 初始值赋值就是 1
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1};
        System.out.println(LengthOfLISA(arr));  // 2
    }
}
