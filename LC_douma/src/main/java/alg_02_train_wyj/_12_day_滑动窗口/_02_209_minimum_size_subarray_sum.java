package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 15:50
 * @Version 1.0
 */
public class _02_209_minimum_size_subarray_sum {
    public int minSubArrayLen1(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum >= target) minLen = Math.min(minLen, i - j + 1);
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        int n = nums.length;
        while (right < n) {
            windowSum += nums[right];
            while (windowSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // 前缀和 + 二分法
    public int minSubArrayLen3(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            int aim = target + prefixSum[i];
            int j = bs(prefixSum, aim);
            if (j < 0) continue;
            if (j <= n) {
                minLen = Math.min(minLen, j - i);
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int bs(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
