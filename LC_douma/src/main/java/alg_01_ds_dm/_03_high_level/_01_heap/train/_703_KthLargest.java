package alg_01_ds_dm._03_high_level._01_heap.train;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 15:00
 * @Version 1.0
 */
public class _703_KthLargest {
}

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.minHeap = new PriorityQueue<>(k);
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            if (minHeap.size() < k) {
                minHeap.add(nums[i]);
            } else if (minHeap.size() == k && nums[i] > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }
    }

    // 时间复杂度：O(logn)
    // 空间复杂度：O(1)
    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else if (minHeap.size() == k && val > minHeap.peek()) {
            minHeap.remove();
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}

