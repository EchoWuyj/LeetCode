package alg_01_ds_wyj._03_high_level._01_heap;

import alg_01_ds_wyj._01_line._01_array.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 19:23
 * @Version 1.0
 */
public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap(E[] arr) {
        this.data = new ArrayList<>(arr);
        for (int i = lastNonLeafIndex(); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-0 does not have parent");
        return (index - 1) / 2;
    }

    private int lastNonLeafIndex() {
        int lastLeafIndex = data.getSize() - 1;
        return parent(lastLeafIndex);
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        E e = data.get(index);
        while (index > 0) {
            E parent = data.get(parent(index));
            if (e.compareTo(parent) <= 0) break;
            data.set(index, parent);
            index = parent(index);
        }
        data.set(index, e);
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new RuntimeException("Heap is Empty");
        }
        return data.getFirst();
    }

    // KeyPoint 删除最大值
    public E removeMax() {
        E max = findMax();
        E last = data.getLast();
        data.set(0, last);
        data.removeLast();
        if (!data.isEmpty()) siftDown(0);
        return max;
    }

    private void siftDown(int index) {
        E e = data.get(index);
        while (leftChild(index) < data.getSize()) {
            int maxNodeIndex = leftChild(index);
            if (rightChild(index) < data.getSize()) {
                if (data.get(rightChild(index)).compareTo(data.get(leftChild(index))) > 0) {
                    maxNodeIndex = rightChild(index);
                }
            }
            if (e.compareTo(data.get(maxNodeIndex)) >= 0) break;
            data.set(index, data.get(maxNodeIndex));
            index = maxNodeIndex;
        }
        data.set(index, e);
    }
}
