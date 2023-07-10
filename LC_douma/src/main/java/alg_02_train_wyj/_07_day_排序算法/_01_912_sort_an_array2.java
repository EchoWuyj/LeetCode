package alg_02_train_wyj._07_day_排序算法;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 15:06
 * @Version 1.0
 */
public class _01_912_sort_an_array2 {

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int n = nums.length;
        sort2(nums, 0, n - 1);
        return nums;
    }

    private void sort1(int[] nums, int low, int high) {
        if (low >= high) return;
        int j = partition(nums, low, high);
        sort1(nums, low, j - 1);
        sort1(nums, j + 1, high);
    }

    private int partition(int[] nums, int low, int high) {
        int randomIndex = new Random().nextInt(high - low + 1) + low;
        swap(nums, randomIndex, high);

        int pivot = nums[high];
        int less = low;
        int great = low;
        for (; great <= high - 1; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, high, less);
        return less;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void sort2(int[] nums, int low, int high) {
        if (low >= high) return;
        int randomIndex = new Random().nextInt(high - low + 1) + low;
        swap(nums, randomIndex, high);
        int pivot = nums[high];
        int less = low;
        int great = high;
        int i = less;
        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort2(nums, low, less - 1);
        sort2(nums, great + 1, high);
    }
}
