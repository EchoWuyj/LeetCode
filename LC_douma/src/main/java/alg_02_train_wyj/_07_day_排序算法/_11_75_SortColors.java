package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 19:03
 * @Version 1.0
 */
public class _11_75_SortColors {
    public void sortColors1(int[] nums) {

    }

    public void sortColors(int[] nums) {

    }

    public void sortColors2(int[] nums) {
        if (nums == null) return;
        int n = nums.length;
        sort(nums, 0, n - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;

        int pivot = nums[high];
        int less = low;
        int great = high;
        int i = low;

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

        sort(nums, low, less - 1);
        sort(nums, great + 1, high);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
