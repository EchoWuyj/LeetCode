package alg_02_train_dm._26_day_动态规划一_二刷._11_300_LengthOfLongestIncrementSubSeq;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 18:34
 * @Version 1.0
 */
public class _11_300_LengthOfLongestIncrementSubSeq {
    /*
        300. 最长 递增 子序列(LIS)

        给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
        子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
        例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

        示例 1：
        输入：nums = [10,9,2,5,3,7,101,18]
        输出：4
        解释：最长递增子序列是 [2,3,7,101]，因此长度为 4

        提示：
        1 <= nums.length <= 2500
        -10^4 <= nums[i] <= 10^4

     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // 不能设置 Integer.MIN_VALUE
        // [7,7,7,7]，全都相等情况，maxLen = 1;
        int maxLen = 1;

        int n = nums.length;
        // 状态：dp[i] 表示以 nums[i] 结尾时最长递增子序列的长度
        int[] dp = new int[n];

        // 状态初始化：单个元素最少有一个递增子序列元素
        Arrays.fill(dp, 1);

        // 直接使用 Arrays.fill(dp, 1) 代替
//        for (int i = 0; i < n; i++) {
//            dp[i] = 1;
//        }

        // 索引   0 1 2 3 4 5
        // nums: -2 1 -3 4 -1
        //        i j
        // dp     1 2 1  3 2

        // 说明：因为是子序列，元素之间可以不连续，故不能只看前一个位置
        //       前面多个元素都是需要比较的，所以得使用 i 和 j 两个指针

        // 状态转移方程：
        // nums[j] > nums[i]：dp[j]= max(1+dp[i],dp[j])
        // nums[j] <= nums[i]：dp[i] 不变

        // i 遍历每个元素
        for (int i = 1; i < n; i++) {
            // j 遍历 i 指针之前的每个元素，都要与 nums[i] 进行比较
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {

                    // 不能直接赋值 dp[i] = dp[j] + 1，需要保证 dp[i] 是最大值，是需要参与比较的
                    // dp[i] 赋值中，靠近 i 位置之前存在 j，使得 nums[i] > nums[j]，对应的 dp[j] 较小，
                    // 将原本大值 dp[i] 覆盖，导致错误结果，故需要加一层 Math.max

                    // 索引  0 1 2 3 4 5
                    // 数组  1 2 1 3 1 4
                    // dp    1 2 1 3
                    //             i
                    //         j     => i = 3，j = 1，nums[3](3) > nums[1](2)，dp[3] = dp[1](2) + 1 = 3
                    //           j   => i = 3，j = 2，nums[3](3) > nums[2](1)，dp[3] = dp[2](1) + 1 = 2
                    //               => 将原来 3 给覆盖了，从而导致错误结果
                    // 故需要 max(dp[j] + 1, dp[i])

                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;
    }
}
