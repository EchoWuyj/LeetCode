package alg_02_train_dm._07_day_排序算法;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 19:43
 * @Version 1.0
 */
public class _01_912_sort_an_array {

    /*
        912. 排序数组
        给你一个整数数组 nums，请你将该数组升序排列。

        提示：
        1 <= nums.length <= 5 * 10^4 => O(nlogn) => 归并和快排(面试重点)，需要非常熟悉，得能写出来
                                     => 必然不考虑 O(n^2) 的算法，因为会超时
                            KeyPoint => 有些情况，即使时间复杂度满足需要，也是可能超时的，测试用例中特别大数据，需要进行常数项优化

        -5 * 104 <= nums[i] <= 5 * 10^4 => 不能使用计数排序，数据范围很大
                                        => 基数排序，桶排序，可以做，但是很麻烦，不推荐
     */

    // KeyPoint 方法一 归并排序 => 超时
    public int[] sortArray1(int[] nums) {
        if (nums == null || nums.length == 1) return nums;
        int n = nums.length;
        int[] tmp = new int[n];
        mergeSort(nums, 0, n - 1, tmp);
        return nums;
    }

    // 归并排序
    private void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) return;
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
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            tmp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        // 合并逻辑：左右部分 i，j 逐个比较
        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                nums[index] = tmp[j++];
            } else if (j == right + 1) {
                nums[index] = tmp[i++];
            } else {
                // KeyPoint 使用 tmp[i]，tmp[j] 比较，而不是使用 nums[i]，nums[j] 比较
                if (tmp[i] <= tmp[j]) {
                    nums[index] = tmp[i++];
                } else {
                    nums[index] = tmp[j++];
                }
            }
        }
    }

    // KeyPoint 方法二 二路快排 => 超时
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // 快排
    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        // 每次分区之后，都能确定分区元素的位置
        // 再去对剩下 low 和 index-1 和 index+1，high 进行排序
        int index = partition(nums, low, high);
        quickSort(nums, low, index - 1);
        quickSort(nums, index + 1, high);
    }

    // 二分切分
    private int partition(int[] nums, int low, int high) {

        // KeyPoint nextInt 说明
        // nextInt(10) 表示生成一个范围在 0 到 9 之间的随机整数
        // nextInt 若不带参数，则生成一个任意范围内的随机整数

        // 随机选一个作为 pivot
        // nextInt(high - low + 1) => 不包括尾端故需要加 1，[0,high - low]
        // nextInt(high - low + 1) + low => [low,high]
        int i = new Random().nextInt(high - low + 1) + low;
        // [i] 和 [high] 交换，从而实现 [high] 时随机选择出来的
        swap(nums, i, high);

        // 依然使用最后一个位置作为 pivot
        int pivot = nums[high];
        // 本质：快慢指针
        int less = low, great = low;
        // high 只是 nums 的右边界，而不是使用 nums.length
        for (; great <= high - 1; great++) {
            if (nums[great] < pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        // 交换 [less] 和 [high] 之后，[less] 为其正确位置
        // less 以左为 < [less] 元素， less 以右 > [less] 元素
        swap(nums, less, high);
        // 分区点索引，该位置元素已经排好序
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //  KeyPoint 方法三 三路快排 => 通过测试用例
    private void quickSort1(int[] nums, int low, int high) {
        if (low >= high) return;

        // 随机选一个作为 pivot
        int j = new Random().nextInt(high - low + 1) + low;
        swap(nums, j, high);
        int pivot = nums[high];
        int less = low, great = high;
        int i = low;
        // 未处理区间为 [i,great] 区间
        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums, i, less);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, great);
                // 和 great 交换后，[great] 还没有处理，i 不能右移
                great--;
            } else {
                i++;
            }
        }

        // [less,great] 都是和 pivot 相等元素，不要再处理了，直接从 less-1 和 great+1 开始处理
        quickSort1(nums, low, less - 1);
        quickSort1(nums, great + 1, high);
    }
}
