package alg_03_high_frequency._01_codetop_2024_01;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 19:02
 * @Version 1.0
 */
public class _04_215_findKthLargest {
    public int findKthLargest(int[] nums, int k) {
        // 堆下元素为题目所求最大值
        // 堆顶为第k个大元素，故使用小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 先加入堆中
            minHeap.add(nums[i]);
            // 判断是否 > k 个元素
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.peek();
    }
}
