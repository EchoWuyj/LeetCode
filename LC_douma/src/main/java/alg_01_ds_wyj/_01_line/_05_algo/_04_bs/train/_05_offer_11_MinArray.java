package alg_01_ds_wyj._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 14:05
 * @Version 1.0
 */
public class _05_offer_11_MinArray {
    public int findMin_1(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minVal = Math.min(nums[i], minVal);
        }
        return minVal;
    }

    public int findMin_2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                return nums[i + 1];
            }
        }
        return nums[0];
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}
