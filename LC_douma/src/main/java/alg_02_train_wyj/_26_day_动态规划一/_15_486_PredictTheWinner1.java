package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 14:47
 * @Version 1.0
 */
public class _15_486_PredictTheWinner1 {

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        return dfs(nums, 0, n - 1) >= 0;
    }

    public int dfs(int[] nums, int i, int j) {
        if (i == j) return nums[i];
        int left = nums[i] - dfs(nums, i + 1, j);
        int right = nums[j] - dfs(nums, i, j - 1);
        return Math.max(left, right);
    }
}
