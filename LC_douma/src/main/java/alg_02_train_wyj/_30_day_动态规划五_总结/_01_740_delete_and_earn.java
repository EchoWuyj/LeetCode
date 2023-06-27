package alg_02_train_wyj._30_day_动态规划五_总结;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 13:09
 * @Version 1.0
 */
public class _01_740_delete_and_earn {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(num, maxVal);
        }
        int[] sum = new int[maxVal + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        return rob(sum);

    }

    private int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }
}
