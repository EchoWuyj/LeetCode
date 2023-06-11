package alg_01_ds_dm._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 18:27
 * @Version 1.0
 */
public class ArrayList<E> {

    // KeyPoint 将泛型替换成 int，代码书写的格式都是一样的
    private E[] data;
    // 数组最大可以装的元素
    private int capacity;
    // 实际数组大小，size指向最后一个元素后面一个位置
    private int size;

    public ArrayList(int capacity) {
        // 泛型不能直接new,得先 new Object[] 再去转 E
        // 创建 Object[] 数组,不是 Object()
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    // 空参构造，默认容量 15
    public ArrayList() {
        // KeyPoint 通过 this 方法调用自己有参构造
        this(15);
    }

    // 有参构造
    public ArrayList(E[] arr) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 不是很严谨
        size = arr.length;
        // bug 修复：加上 capacity 的初始化
        this.capacity = arr.length;
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
     * C(Create)-R(Retrieve)-U(Update)-D(Delete)
     * 向指定索引位置添加一个新元素
     *
     * @param index 指定索引
     * @param e     新元素
     */
    public void add(int index, E e) {
        // size不是capacity,size指向最后一个元素后面一个位置
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, require index >= 0 && index <= size");
        }
        // 扩容
        if (size == data.length) {
            // ArrayList 类的 data，可以直接使用
            resize(data.length * 2);
        }

        // KeyPoint 动态数组 add 不需要重新创建一个 new arr，直接在原 arr 上添加，和静态数组 add 不一样
        //          index之前位置的元素不用移动，只要将 index 及其往后的位置元素后移一个位置即可
        // 最差时间复杂度，循环代码运行最大的次数
        // 即size = data.length && index = 0，时间复杂度 O(n)
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        // size要++，不管是 add，remove 都是要及时更新 size
        size++;
    }

    // 动态扩容/缩容
    private void resize(int newCapacity) {
        // 1 创建一个容量为 newCapacity 的临时数组
        E[] newData = (E[]) new Object[newCapacity];
        // 2 将原来数组中的元素拷贝到新数组中，只是拷贝0~size-1位置元素，而不是整个 data.length
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 3 将新数组覆盖老数组
        data = newData;
        // 4 将容量设置位新容量值
        capacity = newCapacity;
    }

    // 时间复杂度 O(n)
    public void addFirst(E e) {
        add(0, e);
    }

    // 时间复杂度 O(1)
    // size位置就在最后一个元素后面一个位置
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 查询操作
     * 获取 index 索引位置的元素
     *
     * @param index 指定索引
     * @return 返回指定索引对应的元素值
     */
    // 时间复杂度 O(1)
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
     * 查找数组元素 e 所在的索引
     * 如果不存在的元素 e，则返回 -1
     *
     * @param e 指定元素
     * @return 元素 e 所在的索引
     */
    // 时间复杂度 O(n)
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 时间复杂度 O(n)
    public boolean contains(E target) {
        for (E num : data) {
            // KeyPoint 不要使用 num.equals(target)，因为 num 可能为空
            if (target.equals(num)) return true;
        }
        return false;
    }

    /**
     * 修改操作
     * 将 index 索引位置的元素修改为新元素 e
     *
     * @param index 需要修改的索引位置
     * @param e     新设置的元素值
     */
    // 时间复杂度 O(1)
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed, require index >= 0 && index < size");
        }
        data[index] = e;
    }

    /**
     * 删除操作
     * 删除指定索引位置的元素，并且将该值返回
     *
     * @param index 指定索引
     * @return 返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, require index >= 0 && index < size");
        }
        E res = data[index];
        // 考虑最差的情况
        // index = 0 && size = data.length
        // 时间复杂度 O(n)
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        // GC 清除不用的对象
        data[size - 1] = null;

        size--;

        // bug 修复：为了避免时间复杂度震荡，我们在 size == data.length / 4 的时候进行缩容
        //     因为 data.length 有可能不断的减少，所以有可能小于 2 了，若是再缩小就已经为 0 了
        //     则不合理，故在 if 条件中需要再去判断下
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
        // 最好先判断下，提高代码效率
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() { // 返回值类型为 String
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Array: size = %d, capacity = %d, ", size, data.length));
        sb.append("[");
        // 只是将存储的元素遍历出来,故使用size
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}


