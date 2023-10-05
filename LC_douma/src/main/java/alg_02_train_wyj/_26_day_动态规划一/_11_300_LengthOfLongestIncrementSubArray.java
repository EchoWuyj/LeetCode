package alg_02_train_wyj._26_day_动态规划一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 21:04
 * @Version 1.0
 */
public class _11_300_LengthOfLongestIncrementSubArray {
    public static int LengthOfLISA(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1};
        System.out.println(LengthOfLISA(arr));  // 2
    }
}
