package alg_01_ds_dm._03_high_level._01_heap;

import alg_01_ds_dm._01_line._01_array.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 14:36
 * @Version 1.0
 */
public class MaxHeap<E extends Comparable<E>> {

    // 对于完全二叉树使用数组来对其进行存储,效率最高
    private ArrayList<E> data;

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    // KeyPoint 堆化(heapify):将任意数组转成大顶堆
    public MaxHeap(E[] arr) {
        // 将静态数组转动态数组，调用 ArrayList 的 API 来实现 MaxHeap
        this.data = new ArrayList<>(arr);

        // 完全二叉树使用数组来存储，前面一部分是非叶子节点，后面一部分是叶子节点
        // 从第一个非叶子节点往前，依次进行 siftDown 操作，从而实现堆化

        // KeyPoint 时间复杂度 O(n)，不是简单地 n * logn 得 O(nlogn)，这样估算方式不准确
        // 正确解法
        //  1 每个节点向下沉需要比较交换的次数，取决于节点的高度，将所有节点高度和进行累加就是最终的时间复杂度
        //  2 所有节点高度和公式：S = 2n - log2(n)-2 => O(n)

        for (int i = lastNonLeafIndex(); i >= 0; i--) { // O(n)
            siftDown(i); // O(logn)
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

    // 左孩子索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 右孩子索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 父索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    // 最后一个非叶子节点索引
    private int lastNonLeafIndex() {
        // 最后一个叶子节点索引
        int lastLeafIndex = data.getSize() - 1;
        // 最后一个叶子节点的父索引 => 最后一个非叶子节点的索引
        return parent(lastLeafIndex);
    }

    // KeyPoint 1 往大顶堆中添加一个元素
    // 时间复杂度：O(logn)
    public void add(E e) {
        // 1. 先将元素插入到数组的最后
        data.addLast(e);
        // 2. 将最后一个节点 e 上浮
        siftUp(data.getSize() - 1);
    }

    // KeyPoint 上浮 => 将数组中索引为 index 的元素进行上浮
    private void siftUp(int index) {
        // 待插入的元素
        E e = data.get(index);
        // 循环比较并上浮，一直到节点是根节点
        while (index > 0) {
            // 父亲节点
            E parentNode = data.get(parent(index));
            // 比较大小
            // 1 在大顶堆中，每个节点都必须大于或等于它的子节点
            //   e <= parent  => break 停止上浮，这里相等就停止上浮
            // 2 比较过程都是使用待插入的元素 e 进行比较的
            // 3 小顶堆只需要修改 <= 变成 >= 即可
            if (e.compareTo(parentNode) <= 0) break;

            // KeyPoint 使用直接赋值操作来代替交换操作，优化常数时间复杂度
            // 将父节点覆盖子节点
            data.set(index, parentNode);

            // 更新 index，移动到其父节点位置，再去判断
            index = parent(index);
        }
        // 将节点插入到正确的位置上
        data.set(index, e);
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new RuntimeException("Heap is Empty");
        // 数组中第一个值就是最大值
        return data.getFirst();
    }

    // KeyPoint 2 从大顶堆中取出并删除最大值
    // 时间复杂度：O(logn)
    public E removeMax() {
        // 找到最大值，方便后续返回
        E max = findMax();

        // 1. 将最后一个节点替换根节点 => O(1)
        E last = data.getLast();
        data.set(0, last);

        // 2. 删除最后一个节点 => O(1)
        data.removeLast();

        // 3. 将新的根节点做下沉操作 => O(logn) 树的高度
        // KeyPoint 若堆中只有一个节点，在删除之后堆就为空，此时不用下沉，故下沉前先进行判空
        if (!data.isEmpty()) siftDown(0);

        return max;
    }

    // KeyPoint 下沉
    private void siftDown(int index) {
        // 待下沉节点 => 从堆顶开始和左右子节点进行判断
        E e = data.get(index);
        // 完全二叉树中若没有左子节点，必然没有右子节点，因此通过左子节点判断节点是否有子节点
        // 严格 <，不能取等
        while (leftChild(index) < data.getSize()) {
            // 1. 找到节点的左右子节点值最大的那个节点
            // 先默认将左子节点索引设置为最大值索引
            int maxNodeIndex = leftChild(index);
            // 如果有右子节点
            if (rightChild(index) < data.getSize()) {
                // 判断右子节点和左子节点谁大(严格大于)
                if (data.get(rightChild(index)).compareTo(data.get(leftChild(index))) > 0) {
                    maxNodeIndex = rightChild(index);
                }
            }
            // 待下沉节点(父节点值) >= 左右子节点的较大值，直接 break
            if (e.compareTo(data.get(maxNodeIndex)) >= 0) break;

            // 2. 将左右子节点中最大节点值赋值给父节点
            data.set(index, data.get(maxNodeIndex));

            // 更新 index 指针，不断和左右子节点进行判断
            index = maxNodeIndex;
        }
        data.set(index, e);
    }
}
