package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 19:54
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array3 {

    // KeyPoint 方法四 小顶堆 + 大顶堆
    // 针对不同的数据规模，即 k 的取值大小，使用不同的堆结构
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(min(k, n - k))
    public int findKthLargest4(int[] nums, int k) {

        // KeyPoint n = 1000_000，k 不同取值，选择不同堆
        // k = 4 => 小顶堆，没有问题
        // k = 999996 => 小顶堆容量太大，容量 999996，操作也比较耗时
        //            => 问题转化，找到第 n-k+1 小的元素 => 大顶堆

        // KeyPoint '第几大'和'第几小'转换
        // nums 1 2 3 4 5 6，k = 1
        // 倒数第 1 个大元素(6) => 小顶堆 => 保证最大几个元素在二叉堆下面，这样堆顶才能是倒数第 k 大的元素
        // 正数第 6 个小元素(3) => 大顶堆 => 保证最小几个元素在二叉堆下面，这样堆顶才能是正数第 k 小的元素
        // 总结：对应关系 k + k' = 6 + 1 = 7
        // => 若 k = 4，则 k' = 7 - 3 = 3
        // => 倒数第 4 个大元素 和 正数第 3 个小元素
        // 注意：k 和 k' 指的是第几个元素，不是索引

        // KeyPoint 补充：正向索引和反向索引
        // 数值       1   2 3 4  5   6
        // 正向索引   0   1 2 3  4   5
        // 反向索引   n-6 ...   n-2 n-1 => 当 n = 6 代入，正向索引和正向索引是一样的

        int n = nums.length;
        PriorityQueue<Integer> pq;
        int capacity;

        // 提示
        // 1 <= k <= nums.length <= 10^5
        // -104 <= nums[i] <= 104

        // k 比较小，使用小顶堆，求第 k 个最大值
        if (k < n - k) {
            capacity = k;
            pq = new PriorityQueue<>(capacity + 1);
        } else {
            // k 比较大，使用大顶堆，求第 n - k + 1 个最小值
            capacity = n - k + 1;
            pq = new PriorityQueue<>(capacity + 1, (a, b) -> b - a);
        }

        for (int i = 0; i < n; i++) {
            // pq 的 size 比 capacity 多一个，
            // => 为了能将多一个元素放入堆中进行比较，从而调整堆结构
            pq.add(nums[i]);
            // 注意，这里使用 capacity，而不是 k，因为不同堆结构，capacity 值不同
            if (pq.size() > capacity) pq.remove();
        }
        return pq.peek();
    }
}
