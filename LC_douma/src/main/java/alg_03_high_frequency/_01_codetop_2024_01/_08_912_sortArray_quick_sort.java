package alg_03_high_frequency._01_codetop_2024_01;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:12
 * @Version 1.0
 */
public class _08_912_sortArray_quick_sort {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 1) return nums;
        int low = 0, high = n - 1;
        quickSort(nums, low, high);
        return nums;
    }

    public void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        // 每次 quickSort 随机选择一个位置作为 pivot
        // 注意 nextInt 只有一个范围
        int random = new Random().nextInt(high - low + 1) + low;
        swap(nums, random, high);

        int pivot = nums[high];
        // 定义三个指针
        int less = low;
        int greater = high;
        int i = less;

        // i 和 greater 可以取等
        while (i <= greater) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, greater);
                greater--;
            } else {
                i++;
            }
        }
        quickSort(nums, low, less - 1);
        quickSort(nums, greater + 1, high);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
