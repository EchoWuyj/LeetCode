package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:57
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream3 {

    // KeyPoint 方法三 小顶堆
    // 优化思路：不需要对整个数据流中所有数据都去维护数据，无非是想获取第 k 个大元素，其他元素可以不用考虑
    class kthLargest {
        // KeyPoint 将 list 替换 堆结构，自带排序效果
        PriorityQueue<Integer> minHeap;
        int k;

        // 时间复杂度：O(nlogk)
        public kthLargest(int k, int[] nums) {
            // 小顶堆，构造方法中定义容量 k => 实现一
            minHeap = new PriorityQueue<>(k);
            this.k = k;
            for (int num : nums) {
                // KeyPoint 易错点
                // 调用 kthLargest 类的 add 方法，而不是使用 minHeap 自身 add 方法
                add(num);
            }
        }

        // 时间复杂度：O(logk)
        public int add(int val) {
            // 小顶堆容量 < k，直接扔进堆中
            if (minHeap.size() < k) {
                minHeap.add(val);
            } else if (val > minHeap.peek()) {
                // minHeap.size() >= k
                // 对第 k+1 个元素判断，只有 val 大于堆顶，才去调整堆结构，否则直接跳过
                minHeap.remove();
                minHeap.add(val);
            }
            // 返回堆顶元素
            return minHeap.peek();
        }
    }
}
