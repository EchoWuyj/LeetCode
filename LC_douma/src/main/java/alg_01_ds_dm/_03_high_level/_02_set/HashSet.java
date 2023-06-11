package alg_01_ds_dm._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 10:34
 * @Version 1.0
 */

// KeyPoint 存在哈希冲突的 HashSet => 实现比较简单
public class HashSet<E> implements Set<E> {

    // 静态数组
    private E[] data;
    private int size;

    public HashSet() {
        data = (E[]) new Object[10];
        this.size = 0;
    }

    private int hash(E e, int length) {
        // hashCode 值可能为负数，需要转正
        // % length 为了防止索引越界
        return Math.abs(e.hashCode()) % length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) { // O(1)
        // 确定索引
        int index = hash(e, data.length);
        // bug 修复：为 null 的时候才插入
        if (data[index] == null) {
            data[index] = e;
            size++;

            if (size == data.length) { // 扩容均摊时间复杂度 O(1)
                resize(2 * data.length);
            }
        }
    }

    // 扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        // E 散列之后是在数组不同的位置上，因此需要遍历整个 data
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                // 数组长度变化了，需要重新 hash
                int newIndex = hash(data[i], newCapacity);
                newData[newIndex] = data[i];
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) { // O(1)
        int index = hash(e, data.length);
        // bug 修复：在删除某个元素之前，先判断这个元素是否存在，存在的话才删除
        if (data[index] != null) {
            data[index] = null;
            size--;
        }

        // remove 操作暂时不考虑缩容
    }

    @Override
    public boolean contains(E e) { // O(1)
        int index = hash(e, data.length);
        // data[index] 不为 null，则返回 true
        return data[index] != null;
    }
}
