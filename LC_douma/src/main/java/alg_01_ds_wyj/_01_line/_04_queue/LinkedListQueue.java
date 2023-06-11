package alg_01_ds_wyj._01_line._04_queue;

import alg_01_ds_dm._01_line._02_linkedlist.LinkedList;
import alg_01_ds_wyj._01_line._04_queue.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 18:27
 * @Version 1.0
 */
public class LinkedListQueue<E> implements Queue<E> {

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
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
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
