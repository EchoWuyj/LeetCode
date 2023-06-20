package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 10:49
 * @Version 1.0
 */
public class _03_198_house_robber {
    public int rob1(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(nums, n - 1, memo);
    }

    public int dfs(int[] nums, int i, int[] memo) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);

        if (memo[i] != -1) return memo[i];

        int left = nums[i] + dfs(nums, i - 2, memo);
        int right = dfs(nums, i - 1, memo);
        memo[i] = Math.max(left, right);
        return memo[i];
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs1(nums, 0, memo);
    }

    public int dfs1(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];

        int left = dfs1(nums, i + 1, memo);
        int right = nums[i] + dfs1(nums, i + 2, memo);
        memo[i] = Math.max(left, right);
        return memo[i];
    }

    public int rob3(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int rob4(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int pre = nums[0];
        int cur = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int tmp = Math.max(cur, pre + nums[i]);
            pre = cur;
            cur = tmp;
        }
        return cur;
    }
}
