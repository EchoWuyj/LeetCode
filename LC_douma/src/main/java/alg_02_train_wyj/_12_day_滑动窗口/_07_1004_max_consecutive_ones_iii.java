package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 21:51
 * @Version 1.0
 */
public class _07_1004_max_consecutive_ones_iii {
    public int longestOnes1(int[] nums, int k) {
        int ans = 0;
        int left = 0, right = 0;
        int n = nums.length;
        int windowZeroCount = 0;
        while (right < n) {
            if (nums[right] == 0) {
                windowZeroCount++;
                if (windowZeroCount == k + 1) {
                    ans = Math.max(ans, right - left);
                }
            }
            while (windowZeroCount > k) {
                if (nums[left] == 0) windowZeroCount--;
                left++;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }

    public int longestOnes2(int[] nums, int k) {
        int ans = 0;
        int left = 0, right = 0;
        int oneCount = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] == 1) oneCount++;
            while ((right - left + 1) - oneCount > k) {
                if (nums[left] == 1) oneCount--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
