package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:14
 * @Version 1.0
 */
public class _08_912_sortArray_merge_sort {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) return nums;
        int left = 0, right = n - 1;
        int[] tmp = new int[n];
        mergeList(nums, left, right, tmp);
        // 因为在原数组上修改，直接返回 nums 即可
        return nums;
    }

    // 分治函数
    public void mergeList(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        // 不需要返回，直接在 nums 进行修改
        mergeList(nums, left, mid, tmp);
        mergeList(nums, mid + 1, right, tmp);
        // 合并数组
        merge(nums, left, mid, right, tmp);
    }

    public void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        // 循环范围 [left,right]
        for (int i = left; i <= right; i++) {
            tmp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;
        // 循环范围 [left,right]
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j++];
            } else if (j == right + 1) {
                nums[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                nums[index] = tmp[i++];
            } else {
                nums[index] = tmp[j++];
            }
        }
    }
}
