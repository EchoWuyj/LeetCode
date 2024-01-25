package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-19 20:08
 * @Version 1.0
 */
public class _97_912_sortArray_heapSort_02_无注释 {

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 1) return nums;
        int n = nums.length;
        int[] heapArray = new int[n + 1];
        System.arraycopy(nums, 0, heapArray, 1, n);
        buildMaxHeap(heapArray, n);
        for (int i = n; i > 1; i--) {
            swap(heapArray, i, 1);
            adjust(heapArray, 1, i - 1);
        }
        System.arraycopy(heapArray, 1, nums, 0, n);
        return nums;
    }

    public void buildMaxHeap(int[] nums, int n) {
        for (int i = n / 2; i > 0; i--) {
            adjust(nums, i, n);
        }
    }

    public void adjust(int[] nums, int k, int n) {
        nums[0] = nums[k];
        for (int i = k * 2; i <= n; i *= 2) {
            if (i < n && nums[i] < nums[i + 1]) {
                i++;
            }
            if (nums[0] >= nums[i]) {
                break;
            } else {
                nums[k] = nums[i];
                k = i;
            }
        }
        nums[k] = nums[0];
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
