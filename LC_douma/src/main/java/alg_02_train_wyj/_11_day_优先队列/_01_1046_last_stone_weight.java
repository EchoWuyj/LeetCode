package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 10:41
 * @Version 1.0
 */
public class _01_1046_last_stone_weight {
    public int lastStoneWeight1(int[] stones) {
        int n = stones.length;
        for (int i = 0; i < n - 1; i++) {
            Arrays.sort(stones);
            int y = stones[n - 1];
            int x = stones[n - 2];
            if (x == 0) break;
            stones[n - 1] = y - x;
            stones[n - 2] = 0;
        }
        return stones[n - 1];
    }

    public int lastStoneWeight2(int[] stones) {
        int n = stones.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) pq.add(stone);
        while (pq.size() > 1) {
            int y = pq.remove();
            int x = pq.remove();
            int diff = y - x;
            if (diff > 0) pq.add(diff);
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
