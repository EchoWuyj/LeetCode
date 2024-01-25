package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 21:04
 * @Version 1.0
 */
public class _11_300_LengthOfLongestIncrementSubSeq {
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLen = Math.max(dp[i], maxLen);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 2, 3};
        System.out.println(lengthOfLIS(arr));
    }
}
