package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:19
 * @Version 1.0
 */
public class _05_485_max_consecutive_ones {
    public int findMaxConsecutiveOnes1(int[] nums) {
        int res = 0;
        int count = 0;
        int maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        return Math.max(count, maxCount);
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < n) {
            if (nums[right] == 0) {
                res = Math.max(res, right - left);
                left = right + 1;
            }
            right++;
        }
        return Math.max(res, right - left);
    }

    public int findMaxConsecutiveOnes3(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < n) {
            if (nums[right] == 1) {
                res = Math.max(res, right - left + 1);
            } else {
                left = right + 1;
            }
            right++;
        }
        return Math.max(res, right - left + 1);
    }
}
