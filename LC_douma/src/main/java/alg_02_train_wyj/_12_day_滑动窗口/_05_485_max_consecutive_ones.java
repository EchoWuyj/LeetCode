package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:19
 * @Version 1.0
 */
public class _05_485_max_consecutive_ones {
    public int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0;
        int ones = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ones++;
            } else {
                ans = Math.max(ans, ones);
                ones = 0;
            }
        }
        return Math.max(ones, ans);
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        int left = 0, right = 0;
        int ans = 0;
        while (right < n) {
            if (nums[right] == 0) {
                ans = Math.max(ans, right - left);
                left = right + 1;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }

    public int findMaxConsecutiveOnes3(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        int left = 0, right = 0;
        int ans = 0;
        while (right < n) {
            if (nums[right] == 1) {
                ans = Math.max(ans, right - left + 1);
            } else {
                left = right + 1;
            }
            right++;
        }
        return ans;
    }
}
