package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 21:51
 * @Version 1.0
 */
public class _07_1004_max_consecutive_ones_iii {
    public int longestOnes1(int[] nums, int k) {
        int res = 0;
        int left = 0, right = 0;
        int count = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] == 0) {
                count++;
                if (count == k + 1) {
                    res = Math.max(res, right - left);
                }
            }
            while (count > k) {
                if (nums[left] == 0) count--;
                left++;
            }
            right++;
        }
        return Math.max(res, right - left);
    }

    public int longestOnes2(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        int oneCnt = 0;
        while (right < n) {
            if (nums[right] == 1) oneCnt++;
            while ((right - left + 1) - oneCnt > k) {
                if (nums[left] == 1) oneCnt--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
