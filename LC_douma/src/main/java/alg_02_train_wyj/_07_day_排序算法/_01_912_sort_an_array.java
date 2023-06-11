package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 20:43
 * @Version 1.0
 */
public class _01_912_sort_an_array {

    // 归并
    public static int[] sortArray1(int[] nums) {
        if (nums == null || nums.length == 1) return nums;
        int n = nums.length;
        int[] tmp = new int[n];
        mergeSort(nums, 0, n - 1, tmp);
        return nums;
    }

    public static void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, tmp);
        mergeSort(nums, mid + 1, right, tmp);
        merge(nums, left, mid, right, tmp);
    }

    public static void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            tmp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j++];
            } else if (j == right + 1) {
                nums[index] = tmp[i++];
            } else {
                if (tmp[i] <= tmp[j]) {
                    nums[index] = tmp[i++];
                } else {
                    nums[index] = tmp[j++];
                }
            }
        }
    }

    public int[] sortArray2(int[] nums) {
        if (nums == null || nums.length == 1) return nums;
        int n = nums.length;
        int[] tmp = new int[n];
        quickSort2(nums, 0, n - 1);
        return nums;
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int index = partition(nums, low, high);
        quickSort(nums, low, index - 1);
        quickSort(nums, index + 1, high);
    }

    private int partition(int[] nums, int low, int high) {
        int less = low, great = low;
        int randomIndex = new Random().nextInt(high - low + 1) + low;
        swap(nums, randomIndex, high);
        int pivot = nums[high];
        for (; great <= high - 1; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void quickSort2(int[] nums, int low, int high) {
        if (low >= high) return;
        int randomIndex = new Random().nextInt(high - low + 1) + low;
        swap(nums, randomIndex, high);
        int pivot = nums[high];
        int less = low, great = high;
        int i = low;
        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                i++;
                less++;
            } else if (nums[i] > pivot) {
                swap(nums, i, great);
                great--;
            } else {
                i++;
            }
        }

        quickSort2(nums, low, less - 1);
        quickSort2(nums, great + 1, high);
    }

    public static void main(String[] args) {
        int[] test = {5, 2, 3, 1};
        System.out.println(Arrays.toString(sortArray1(test)));
    }
}
