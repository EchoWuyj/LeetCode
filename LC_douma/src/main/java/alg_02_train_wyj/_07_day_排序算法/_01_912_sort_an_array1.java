package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 20:43
 * @Version 1.0
 */
public class _01_912_sort_an_array1 {

    public int[] sortArray1(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int n = nums.length;
        int[] tmp = new int[n];
        mergeSort(nums, 0, n - 1, tmp);
        return nums;
    }

    // 分治算法
    public void mergeSort(int[] nums, int left, int right, int[] tmp) {
        // 一个元素本身就是有序的，故已经到递归边界
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, tmp);
        mergeSort(nums, mid + 1, right, tmp);
        // merge 操作在原数组 nums 上操作，故没有返回值
        merge(nums, left, mid, right, tmp);
    }

    // 合并逻辑
    public void merge(int[] data, int left, int mid, int right, int[] tmp) {
        // 从 left 到 right 将元素赋值给 tmp
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int i = left;
        int j = mid + 1;
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                data[index] = tmp[j++];
            } else if (j == right + 1) {
                data[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[index] = tmp[i++];
            } else {
                data[index] = tmp[j++];
            }
        }
    }
}
