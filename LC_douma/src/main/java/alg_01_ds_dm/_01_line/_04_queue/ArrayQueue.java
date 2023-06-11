package alg_01_ds_dm._01_line._04_queue;

import alg_01_ds_dm._01_line._01_array.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 11:45
 * @Version 1.0
 */
public class ArrayQueue<E> implements Queue<E> {

    // 动态数组
    // ArrayQueue<E> 和 Queue<E> 传入参数都是有 E，因此 ArrayList<E> 也得有 E
    private final ArrayList<E> data;

    public ArrayQueue() {
        data = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 队头 ... 队尾
    //  ← ..... ←
    @Override
    public void enqueue(E e) { // O(1)
        data.addLast(e);
    }

    @Override
    public E dequeue() { // O(n) <= 数组的头元素移除，后面的元素需要从后往前依次移动一个位置
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        // 数组左边是队首，决定是是从左往右进行遍历
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
