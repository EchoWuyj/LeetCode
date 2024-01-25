package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-09 15:05
 * @Version 1.0
 */
public class _01_912_sort_an_array2_推荐 {

    // KeyPoint 方法二 二路快排 => 超时
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        int n = nums.length;
        quickSort(nums, 0, n - 1);
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

        // KeyPoint 随机确定分区 => 即随机选一个元素作为 pivot

        // KeyPoint 关于 nextInt 说明
        // 1.nextInt(10) 表示生成一个范围在 [0,9] 随机整数
        //   nextInt 若不带参数，则生成一个任意范围内的随机整数
        // 2.nextInt(high - low + 1) => [0,high - low] => 因为 nextInt 不包括尾端，故需要加 1
        //   nextInt(high - low + 1) + low => [low,high]
        int i = new Random().nextInt(high - low + 1) + low;

        // [i] 和 [high] 交换，从而实现 [high] 时随机选择出来的
        swap(nums, i, high);

        // 依然使用最后一个位置作为 pivot
        int pivot = nums[high];

        // 本质：快慢指针
        int less = low, great = low;
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
        int random = new Random().nextInt(high - low + 1) + low;
        swap(nums, random, high);

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
