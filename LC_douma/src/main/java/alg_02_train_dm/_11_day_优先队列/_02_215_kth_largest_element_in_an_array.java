package alg_02_train_dm._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 23:41
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array {
    /*

        215 号算法题：数组中的第 K 个最大元素
        在未排序的数组中找到第 k 个最大的元素。
        请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

        输入: [3,2,1,5,6,4] 和 k = 2
        输出: 5

        输入: [3,2,3,1,2,4,5,5,6] 和 k = 4  => 5，5 相同元素属于两个不同最大元素
        输出: 4

        你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

        提示：
        1 <= k <= nums.length <= 10^5
        -104 <= nums[i] <= 104
     */

    // KeyPoint 方法一 排序解法
    // 时间复杂度：O(nlogn)
    // => 数据规模 10^5 ，log(10^5) < 20，nlogn = 20 * 10^5 = 2 * 10^6，故 O(nlogn) 可以通过
    // 详见 _02_Note_Data_Scale
    // 空间复杂度：快排 O(logn)，归并排序 O(n)
    // => 数据规模很大，使用归并排序，否则使用快排
    public int findKthLargest1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // 倒数第 1 个大元素 nums[n-1]
        // 倒数第 k 个大元素 nums[n-k]
        return nums[n - k];
    }

    // KeyPoint 方法二 小顶堆
    // 排序解法时间复杂度高的原因：对所有的元素进行全量排序
    // 但实际上，只需要确定 k 个最大元素即可，其他元素可以不关心
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(k)
    public int findKthLargest2(int[] nums, int k) {
        // 设置 pq 元素个数为 k
        // 小顶堆，保证顶堆是最小元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.remove();
                pq.add(nums[i]);
            }
        }
        // 小顶堆一共 k 个元素，其中堆顶为第 k 个最大元素
        return pq.peek();
    }

    // KeyPoint 方法三 小顶堆(另一种实现)
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(k)
    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int i = 0; i < nums.length; i++) {
            // k+1 个元素加入 pq 后
            pq.add(nums[i]);
            // pq 维护 k 个元素，删除多个多余的 1 个元素
            if (pq.size() > k) pq.remove();
        }
        return pq.peek();
    }

    // KeyPoint 方法四 小顶堆 + 大顶堆
    // 针对不同的数据规模，即 k 的取值大小，使用不同的堆结构
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(min(k, n - k))
    public int findKthLargest4(int[] nums, int k) {

        // n = 1000000
        // k 不同数据规模
        // k = 4 => 小顶堆，没有问题
        // k = 999996 => 小顶堆容量太大，操作也比较耗时
        //            => 问题转化，找到第 n-k+1 小的元素 => 大顶堆

        // 1 2 3 4 5 6
        // k = 4
        // 倒数：第 4 个最大元素(3) => 小顶堆 => 保证最大几个元素在二叉堆下面，这样堆顶才能是倒数第 k 大的元素
        // 正数：第 3 个最小元素(3) => 大顶堆 => 保证最小几个元素在二叉堆下面，这样堆顶才能是正数第 k 小的元素
        // k = 4 => n = 6，n-k+1 = 3

        // 补充：正向索引和反向索引
        // 数值       1   2 3 4  5   6
        // 正向索引   0   1 2 3  4   5
        // 反向索引   n-6 ...   n-2 n-1

        int n = nums.length;
        PriorityQueue<Integer> pq;
        int capacity;
        // k 比较小，小顶堆，求第 k 个最大值
        if (k < n - k) {
            capacity = k;
            pq = new PriorityQueue<>(capacity + 1);
        } else { // k 比较大，大顶堆，求第 n - k + 1 个最小值
            capacity = n - k + 1;
            pq = new PriorityQueue<>(capacity + 1, (a, b) -> b - a);
        }

        for (int i = 0; i < nums.length; i++) {
            // pq 的 size 比 capacity 多一个，为了能将多一个元素放入堆中进行比较，从而调整堆结构
            pq.add(nums[i]);
            // 注意，这里使用 capacity，而不是 k，因为不同堆结构，capacity 值不同
            if (pq.size() > capacity) pq.remove();
        }

        return pq.peek();
    }

    // KeyPoint 方法五 快速排序分区优化 => 算法模板之一，后续还会使用到
    // 只要将第 k 大元素放在 n-k 索引位置上，结束操作
    // 把一个元素放到排序之后正确位置上 => 快速排序的分区操作，每次分区操作都将分区点放在排排序之后正确的位置上
    // 时间复杂度：O(n) => 很难证明，记住结论
    // 空间复杂度：O(1)
    public int findKthLargest5(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        // 若 nums.length 为 n，k 为 2，即第 2 大元素，最大索引 n-1，第 2 大元素索引 n-2
        // 同理 => 即第 k 大元素，第 k 大元素索引 n-k
        int target = nums.length - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            // 使用 "二分思想"
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) { // 小于 => 右边找
                left = pivotIndex + 1;
            } else { // 大于 => 左边找
                right = pivotIndex - 1;
            }
        }
    }

    // 若 Random 种子相同，则 Random 每次返回的随机序列也就会相同
    // 通过 System.currentTimeMillis()，从而避免 Random 种子相同
    private Random random = new Random(System.currentTimeMillis());

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
