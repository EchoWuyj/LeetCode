package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 23:32
 * @Version 1.0
 */
public class _02_674_longest_continuous_increasing_subsequence {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int slow = 0;
        int n = nums.length;
        for (int fast = 1; fast < n; fast++) {
            if (nums[fast - 1] >= nums[fast]) {
                slow = fast;
                continue;
            }
            res = Math.max(res, fast - slow + 1);
        }
        return res;
    }
}
