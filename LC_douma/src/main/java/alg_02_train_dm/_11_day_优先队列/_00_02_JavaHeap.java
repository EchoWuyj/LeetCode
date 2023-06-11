package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 23:31
 * @Version 1.0
 */
public class _00_02_JavaHeap {
    public static void main(String[] args) {

        // 优先队列 底层实现 优先队列

        // KeyPoint 默认是小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        // 大顶堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> b - a);

        // 添加和删除操作成对使用
        // add 和 remove
        // offer 和 poll

        // 往堆中添加元素
        pq.add(3);
        pq.offer(4);

        // 删除堆顶元素
        Integer top1 = pq.remove();
        Integer top2 = pq.poll();

        // 拿到堆顶元素
        Integer e = pq.peek();
    }
}
