package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 20:02
 * @Version 1.0
 */
public class _10_494_target_sum {
    private int res = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, 0, target);
        return res;
    }

    public void dfs(int[] nums, int index, int sum, int target) {
        if (index == nums.length) {
            if (sum == target) res++;
            return;
        }
        dfs(nums, index + 1, sum + nums[index], target);
        dfs(nums, index + 1, sum - nums[index], target);
    }

    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = neg; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[neg];
    }
}
