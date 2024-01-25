package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-11-19 23:35
 * @Version 1.0
 */
public class _09_LCR_159_inventoryManagement {
    public int[] inventoryManagement(int[] stock, int cnt) {
        PriorityQueue<Integer> maxHeap
                = new PriorityQueue<>((a, b) -> b - a);
        int n = stock.length;
        for (int i = 0; i < n; i++) {
            maxHeap.add(stock[i]);
            if (maxHeap.size() > cnt) {
                maxHeap.remove();
            }
        }
        int[] res = new int[cnt];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;
    }
}
