package alg_02_train_wyj._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 19:56
 * @Version 1.0
 */
public class _02_167_TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            int index = bs(nums, i + 1, n - 1, num2);
            if (index != -1) {
                return new int[]{i + 1, index + 1};
            }
        }
        return null;
    }

    private int bs(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
