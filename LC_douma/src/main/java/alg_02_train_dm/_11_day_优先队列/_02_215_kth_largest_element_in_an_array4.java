package alg_02_train_dm._11_day_优先队列;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 19:55
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array4 {

    // KeyPoint 方法五 优化 => 快速排序分区
    // 时间复杂度：O(n) => 很难证明，记住结论
    // 空间复杂度：O(1)
    public int findKthLargest5(int[] nums, int k) {

        // 不管局部排序，只要将第 k 大元素放在 n-k 索引位置上(升序排列)，即可结束操作

        // 把一个元素放到排序之后正确位置上 => 是快速排序分区操作
        // => 快速排序每次分区操作，将分区点放在排排序之后正确的位置上
        // => 该算法是代码模板之一，后续还会使用到

        //              pivot
        //                ↓
        // nums 3 2 1 4 6 5，k = 2
        //          ↓ 分区
        // nums 3 2 1 4 5 6，k = 2
        //              ↑
        //        若分区索引为：n-k => pivot 即为第 k 大元素，结束操作

        // 快速排序分区 + 二分查找

        //                  pivot
        //                    ↓
        // nums 3 2 1 6 5 8 7 4，k = 4，target = 8 - 4 = 4
        //          ↓ 分区
        // nums 3 2 1 4 6 5 8 7
        //            ↑ ↑_____↑
        //   分区索引 3 < 4，仅仅对后面一部分分区

        //                  pivot
        //                    ↓
        // nums 3 2 1 4 6 5 8 7
        //           ↓ 分区
        // nums 3 2 1 4 6 5 7 8
        //                  ↑
        //         分区索引 6 > 4
        // ...

        int n = nums.length;
        int left = 0, right = n - 1;
        // 若 nums.length 为 n，k 为 2，即第 2 大元素
        // => 最大索引 n-1，第 2 大元素索引 n-2
        // => 即第 k 大元素，第 k 大元素索引 n-k

        // 使用二分查找，来查找 n-k
        // 将 n-k 作为 target
        int target = n - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            // 使用 "二分思想"
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                // 小于 => 右边找
                left = pivotIndex + 1;
            } else {
                // 大于 => 左边找
                right = pivotIndex - 1;
            }
        }
    }

    // 若 Random 种子相同，则 Random 每次返回的随机序列也就会相同
    // 通过 System.currentTimeMillis()，从而避免 Random 种子相同
    private Random random = new Random(System.currentTimeMillis());

    // 二路快排
    private int partition(int[] nums, int low, int high) {
        // 使用随机数，随机快排
        if (high > low) {
            // pivotIndex 属于 [low,high]，比较好理解
            int pivotIndex = random.nextInt(high - low + 1) + low;

            // 不推荐使用这种方式生成 pivotIndex，pivotIndex 属于 [low+1，high)
//            int pivotIndex = low + 1 + random.nextInt(high - low);
            swap(nums, pivotIndex, high);
        }
        int pivot = nums[high];
        int less = low, great = low;
        for (; great < high; great++) {
            if (nums[great] < pivot) {
                // great 和 less 交换，less 以左元素为小于 less 的部分
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
}
