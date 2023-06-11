package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-17 11:14
 * @Version 1.0
 */
public class _15_905_sort_array_by_parity {
    public int[] sortArrayByParity1(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                res[i] = nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) {
                res[i] = nums[i];
            }
        }
        return res;
    }

    public int[] sortArrayByParity2(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                res[left++] = nums[i];
            }
            if (nums[i] % 2 == 1) {
                res[right--] = nums[i];
            }
        }
        return res;
    }

    public int[] sortArrayByParity3(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) tmp[i] = nums[i];
        Arrays.sort(tmp, (o1, o2) -> o1 % 2 - o2 % 2);
        for (int i = 0; i < n; i++) nums[i] = tmp[i];
        return nums;
    }

    public int[] sortArrayByParity4(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        int less = 0, great = 0;
        for (; great < n; great++) {
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            if (nums[less] % 2 == 0) less++;
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortArrayByParity(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            if (nums[left] % 2 > nums[right] % 2) {
                swap(nums, left, right);
            }
            if (nums[left] % 2 == 0) left++;
            if (nums[right] % 2 == 1) right--;
        }
        return nums;
    }
}
