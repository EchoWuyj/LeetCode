package alg_03_high_frequency._01_codetop_2024_01_Top100;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 19:02
 * @Version 1.0
 */
public class _04_215_findKthLargest {

    // 组中的第K个最大元素
    // 小根堆
    public int findKthLargest(int[] nums, int k) {

        // 堆结构最终获取元素位置只能是从堆顶，堆顶位置倒推是小顶堆还是大顶堆
        // 堆下元素为题目所求最大值，堆顶为第K个大元素，故使用小顶堆

        // 注意：牛客在线 OJ 类名得大写之后，才会有相应的自动提示 Prio 提示 PriorityQueue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 先将元素加入堆中，再去判断堆元素个数是否 > k
            minHeap.add(nums[i]);
            // minHeap 加入元素之后，minHeap.size() > k，则 minHeap.size() 保留 k 个元素
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        // 返回堆顶
        return minHeap.peek();
    }
}
