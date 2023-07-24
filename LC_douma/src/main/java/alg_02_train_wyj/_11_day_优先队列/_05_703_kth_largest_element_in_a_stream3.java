package alg_02_train_wyj._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:59
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream3 {

    class kthLargest {
        PriorityQueue<Integer> minHeap;
        int k;

        public kthLargest(int k, int[] nums) {
            minHeap = new PriorityQueue<>(k);
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.add(val);
            } else {
                if (val > minHeap.peek()) {
                    minHeap.remove();
                    minHeap.add(val);
                }
            }
            return minHeap.peek();
        }
    }
}
