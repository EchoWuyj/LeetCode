package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 19:43
 * @Version 1.0
 */
public class _01_912_sort_an_array1_推荐 {

    /*
        912. 排序数组
        给你一个整数数组 nums，请你将该数组升序排列。

        提示：
        1 <= nums.length <= 5 * 10^4 => O(nlogn) => 归并和快排(面试重点)，需要非常熟悉，得能写出来
                                     => 必然不考虑 O(n^2) 的算法，因为会超时
                                     => 有些情况，即使时间复杂度满足需要，也是可能超时的，测试用例中特别大数据，
                                        需要进行常数项优化

        -5 * 10^4 <= nums[i] <= 5 * 10^4 => 不能使用计数排序，数据范围很大
                                         => 基数排序，桶排序，可以做，但是很麻烦，不推荐
     */

    // KeyPoint 方法一 归并排序 => 超时
    public int[] sortArray1(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int n = nums.length;
        // 临时数组
        int[] tmp = new int[n];
        mergeSort(nums, 0, n - 1, tmp);
        return nums;
    }

    // 归并排序
    private void mergeSort(int[] nums, int left, int right, int[] tmp) {

        if (left >= right) return;
        // int mid = (left + right) / 2;
        int mid = left + (right - left) / 2;

        // 左排序
        mergeSort(nums, left, mid, tmp);
        // 右排序
        mergeSort(nums, mid + 1, right, tmp);

        // 左右合并
        merge(nums, left, mid, right, tmp);
    }

    // 合并(实现二)
    private void merge(int[] nums, int left, int mid, int right, int[] tmp) {
        // 整体数组长度
        int n = nums.length;
        // 将 nums 赋值给 tmp
        for (int i = 0; i < n; i++) {
            // 往后的操作，都是基于 tmp，而不是 num
            // 记忆：tmp 干活，num 享受
            tmp[i] = nums[i];
        }

        // i j 指针
        int i = left;
        int j = mid + 1;

        // 合并逻辑：左右部分 i，j 逐个比较
        // 在给定区间 [left,right] 进行比较
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j++];
            } else if (j == right + 1) {
                nums[index] = tmp[i++];
            } else {
                // KeyPoint 易错点
                // 1.使用 tmp[i]，tmp[j] 比较，而不是使用 nums[i]，nums[j] 比较
                // 2.使用 tmp 赋值给 num，所以比较对象是 tmp，而不是 num 赋值给 num
                if (tmp[i] <= tmp[j]) {
                    nums[index] = tmp[i++];
                } else {
                    nums[index] = tmp[j++];
                }
            }
        }
    }
}
