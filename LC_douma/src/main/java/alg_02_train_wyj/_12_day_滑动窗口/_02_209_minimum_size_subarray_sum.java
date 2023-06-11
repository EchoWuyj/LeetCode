package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 15:50
 * @Version 1.0
 */
public class _02_209_minimum_size_subarray_sum {
    public int minSubArrayLen1(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    ans = Math.min(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            while (windowSum >= target) {
                ans = Math.min(ans, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 前缀和 + 二分法
    public int minSubArrayLen3(int target, int[] nums) {
        if (nums == null) return 0;
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        int n = prefixSum.length;
        prefixSum[0] = 0;
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int t = target + prefixSum[i - 1];
            int j = help(prefixSum, t);
            if (j < 0) continue;
            if (j <= nums.length) {
                ans = Math.min(ans, j - i + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int help(int[] nums, int target) {
        if (nums == null) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
