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
        int notRobLastHouse = dfs(nums, 0, n - 2);
        int notRobFirstHouse = dfs(nums, 1, n - 1);
        return Math.max(notRobLastHouse, notRobFirstHouse);
    }

    public int dfs(int[] nums, int start, int end) {
        if (start - end + 1 == 1) return nums[start];
        int n = nums.length;
        int[] dp = new int[n];

        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    public int dfs1(int[] nums, int start, int end) {
        if (start - end + 1 == 1) return nums[start];
        int n = nums.length;

        int prev = nums[start];
        int curr = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            int tmp = Math.max(curr, prev + nums[i]);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
