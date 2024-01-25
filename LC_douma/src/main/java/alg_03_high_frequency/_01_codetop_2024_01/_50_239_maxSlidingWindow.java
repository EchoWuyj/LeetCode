package alg_03_high_frequency._01_codetop_2024_01;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 12:44
 * @Version 1.0
 */
public class _50_239_maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // 窗口中获取最大值 => 大顶堆，通过堆顶维护最大值
        // 堆中不仅仅存储数组元素，还需要存储数组元素对应的索引，从而方便
        // 判断该元素是否还在窗口中，将两个信息整体封装成数组形式存到堆中

        // 前面泛型 <> 中，需要传入具体数据类型
        PriorityQueue<int[]> maxHeap
                // 后面泛型 <> 中，不需要传入具体数据类型，但是不能丢 <>、否则无法识别数据类型
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);

        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[]{nums[i], i});
        }

        res[0] = maxHeap.peek()[0];

        for (int i = k; i < n; i++) {
            maxHeap.add(new int[]{nums[i], i});
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.remove();
            }
            // 后续的 i 从 k 开始，故这里索引需要 i - k，同时避免和 res[0] 重复，故还得加 1
            res[i - k + 1] = maxHeap.peek()[0];
        }
        return res;
    }
}
