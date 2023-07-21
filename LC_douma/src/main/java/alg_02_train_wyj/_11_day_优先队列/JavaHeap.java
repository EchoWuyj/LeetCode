package alg_02_train_wyj._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 19:17
 * @Version 1.0
 */
public class JavaHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> b - a);
    }
}
