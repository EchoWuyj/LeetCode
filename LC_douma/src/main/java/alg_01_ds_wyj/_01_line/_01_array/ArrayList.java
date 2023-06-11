package alg_01_ds_wyj._01_line._01_array;



/**
 * @Author Wuyj
 * @DateTime 2023-03-09 14:06
 * @Version 1.0
 */
public class ArrayList<E> {
    private E[] data;
    private int capacity;
    private int size;

    public ArrayList(int capacity) {
        this.data = (E[]) (new Object[capacity]);
        this.capacity = capacity;
        this.size = 0;
    }

    public ArrayList() {
        this(15);
    }

    public ArrayList(E[] arr) {
        this.data = (E[]) (new Object[arr.length]);
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        this.size = arr.length;
        this.capacity = arr.length;
    }

    public static void main(String[] args) {
        int[] arr = new int[4];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * 新增操作
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, require index >= 0 && index <= size");
        }
        // 扩容
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    // 时间复杂度 O(n)
    public void addFirst(E e) {
        add(0, e);
    }

    // 时间复杂度 O(1)
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 查询操作
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, require index >= 0 && index < size");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    /**
     * 查找
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) return i;
        }
        return -1;
    }

    public boolean contains(E target) {
        for (E num : data) {
            if (target.equals(num)) return true;
        }

        return false;
    }

    /**
     * 修改操作
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed, require index >= 0 && index < size");
        }
        data[index] = e;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, require index >= 0 && index < size");
        }
        E res = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        data[size - 1] = null;
        size--;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    /**
     * 删除第一个元素
     *
     * @return 第一个元素值
     */
    // 时间复杂度 O(n)
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return 最后一个元素的值
     */
    // 时间复杂度 O(1)
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定元素
     *
     * @param e 需要删除的元素
     */
    // 时间复杂度 O(n)
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(
                "Array: size = %d, capacity = %d, ", size, data.length));
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
