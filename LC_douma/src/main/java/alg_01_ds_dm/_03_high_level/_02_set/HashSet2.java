package alg_01_ds_dm._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 10:55
 * @Version 1.0
 */

// KeyPoint 开放寻址法(线性探测) => 哈希冲突
public class HashSet2<E> implements Set<E> {

    // 数组中存储是 Item，除了数据 data，还有标记是否被删除的 isDeleted
    private class Item<E> {
        E data;
        boolean isDeleted;

        public Item(E data) {
            this.data = data;
            // 初始化过程 默认是没有标记删除的
            this.isDeleted = false;
        }

        @Override
        public int hashCode() {
            // 重写 Item 的 hashCode 方法，将其替换成 data 的 hashCode
            return data.hashCode();
        }
    }

    private Item<E>[] items;
    private int size;
    private int deleteCount; // 用于记录有多少标记删除的元素

    // KeyPoint 装载因子 => 控制 HashSet 数组中有一定比例的空闲位置 => 减少哈希冲突的概率
    // 1 数组中空余的位置不多，此时哈希冲突的概率大大提高，此时需要不断往后遍历
    //   找第一个空位，则可能需要找很多空位，从而导致 HashSet 的性能变差
    // 2 在 loadFactor 的值比较小时，开放寻址法和拉链法来解决哈希冲突，add，remove，contains 的时间复杂度平均都是 O(1)
    private double loadFactor = 0.75; // 经验值 0.75

    public HashSet2() {
        items = new Item[10];
        this.size = 0;
        this.deleteCount = 0;
    }

    // 外部传入 loadFactor
    public HashSet2(int loadFactor) {
        this();
        this.loadFactor = loadFactor;
    }

    private int hash(E e, int length) {
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
    public void add(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);

        // bug 修复：add 前先判断是否存在元素 e
        while (items[index] != null) {
            // 说明新增的 e 已经存在，不用再新增重复的 e，则直接返回
            if (!items[index].isDeleted && e.equals(items[index].data)) return;
            // index 指针后移
            index++;
            // 防止 index 越界
            index = index % items.length;
        }

        // 插入元素，index 对应 null 或者是已经删除的元素位置
        items[index] = new Item<>(e);
        size++;

        if (size >= items.length * loadFactor) {
            resize(2 * items.length);
        }
    }

    private void resize(int newCapacity) {
        Item<E>[] newData = new Item[newCapacity];
        for (int i = 0; i < items.length; i++) {
            // 删除的元素不用放进新数组中
            if (items[i] != null && !items[i].isDeleted) {
                int newIndex = hash(items[i].data, newCapacity);
                newData[newIndex] = items[i];
            }
        }
        // 所有标记为删除的元素都清理掉了
        deleteCount = 0;
        items = newData;
    }

    @Override
    public void remove(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);
        // 找到等于 e 且没有标记为删除的元素
        while (items[index] != null &&
                (!e.equals(items[index].data) || items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }
        if (items[index] != null && !items[index].isDeleted) {
            // 标记该元素是删除的，但是实际上没有真正删除，但插入时还是会被覆盖
            items[index].isDeleted = true;
            size--;
            deleteCount++;
        }

        // 如果标记为删除的元素太多的话，我们进行 resize，清除标记为删除的元素
        // KeyPoint：这里可能会产生时间复杂度震荡
        if (deleteCount + size >= items.length * loadFactor) {
            resize(items.length);
        }
    }

    @Override
    public boolean contains(E e) { // 最好：O(1)，最坏：O(n)
        int index = hash(e, items.length);

        // 找到等于 e 且没有标记为删除的元素
        while (items[index] != null &&
                (!e.equals(items[index].data) || items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }

        //
        return items[index] != null && !items[index].isDeleted;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet2<>();
        set.add(11);
        set.add(21);

        System.out.println(set.contains(21));
        System.out.println(set.contains(11));
    }
}
