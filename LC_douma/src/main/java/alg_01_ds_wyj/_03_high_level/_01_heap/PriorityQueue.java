package alg_01_ds_wyj._03_high_level._01_heap;

import alg_01_ds_dm._03_high_level._01_heap.MaxHeap;
import alg_01_ds_wyj._01_line._04_queue.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 21:09
 * @Version 1.0
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    // 使用二叉堆来实现
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
