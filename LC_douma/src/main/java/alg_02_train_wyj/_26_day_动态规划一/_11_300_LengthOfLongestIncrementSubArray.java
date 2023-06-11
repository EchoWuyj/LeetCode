package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 21:04
 * @Version 1.0
 */
public class _11_300_LengthOfLongestIncrementSubArray {
    public static int LengthOfLISA(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        int len = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = 1 + dp[i - 1];
                len = Math.max(len, dp[i]);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1};
        System.out.println(LengthOfLISA(arr));  // 2
    }
}
