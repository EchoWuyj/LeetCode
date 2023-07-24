package alg_02_train_wyj._11_day_优先队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 14:37
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements {

    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>(k + 1, (a, b) -> count.get(a) - count.get(b));

        for (int num : count.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.remove();
        }

        int[] res = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.remove();
        }
        return res;
    }
}
