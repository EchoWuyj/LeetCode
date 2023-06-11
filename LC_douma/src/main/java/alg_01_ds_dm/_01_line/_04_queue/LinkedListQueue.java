package alg_01_ds_dm._01_line._04_queue;

import alg_01_ds_dm._01_line._02_linkedlist.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 12:00
 * @Version 1.0
 */
public class LinkedListQueue<E> implements Queue<E> {

    // 链表
    private LinkedList<E> data;

    public LinkedListQueue() {
        data = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void enqueue(E e) { // O(n) <= 遍历到队列最后的位置再去入队
        data.addLast(e);
    }

    @Override
    public E dequeue() {  // O(1)
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue：队首 [");
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i));
            if (i != data.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
