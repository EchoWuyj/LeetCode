package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 11:28
 * @Version 1.0
 */
public class _04_213_house_robber_ii {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int first = dp(nums, 0, n - 2);
        int last = dp(nums, 1, n - 1);
        return Math.max(first, last);
    }

    public int dp(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        int[] dp = new int[nums.length];

        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    public int dp1(int[] nums, int start, int end) {
        if (end == start) return nums[start];
        int pre = nums[start];
        int cur = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            int tmp = Math.max(cur, pre + nums[i]);
            pre = cur;
            cur = tmp;
        }
        return cur;
    }
}
