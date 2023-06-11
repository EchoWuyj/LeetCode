package alg_01_ds_dm._01_line._04_queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 12:52
 * @Version 1.0
 */

// 通过循环队列，移动指针，而不是移动数据，从而将出队时间复杂度 O(n)->O(1)
public class LoopQueue<E> implements Queue<E> {

    // 静态数组 => 手动维护扩容
    private E[] data;
    // KeyPoint 移动指针，而不是移动数据，从而降低时间复杂度：O(n) -> O(1)
    // head 指向队首元素
    private int head;
    // tail 指向队尾元素后面一个空的位置
    private int tail;
    // 队列中元素个数
    private int size;

    public LoopQueue() {
        // 空参构造，默认是 20 大小
        this(20);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        // 头尾指针
        head = tail = 0;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        // 规定 head 指针和 tail 指针在同一个位置表示为队列为空
        return head == tail;

        // 这里也可以使用 size 来判断队列是否空的
        // 参考 issue：https://gitee.com/douma_edu/douma-algo/issues/I4WGCE
    }

    // 当前队列的容量，最大能装元素大小，而不是实际装的元素个数
    public int getCapacity() {
        // 通过牺牲队列中一个元素位置，为区分队列是空还是满了，故为 data.length - 1
        return data.length - 1;
    }

    // 进队
    @Override
    public void enqueue(E e) {  // O(1) 均摊
        // KeyPoint 进队前判断队列是否满了 => tail 和 head 是否差一个位置
        // 通过牺牲队列中一个元素位置，为区分队列是空还是满了
        // 1 tail + 1 表示 tail 加上一个位置，判断是否和 head 相等
        // 2 tail 最大索引是比 data.length 少 1 的，但是 tail + 1 可能存在越界情况，故需要 % data.length
        if ((tail + 1) % data.length == head) {
            // 队列满了，队列容量扩容 2 倍，实际存储元素的个数
            // 实际申请数组大小还要 + 1，用来区分队列是空还是满
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 向右移动 tail 指针
        // 若 len 为 5，则 tail 最多为 4，所以对 5 取余，又回到索引为 0 的位置，从而实现循环
        // tail 只能在 len-1 内移动，不会越界
        tail = (tail + 1) % data.length;
        size++;

        // 这里也可以使用 size 来判断队列是否满了，参考 issue
        // https://gitee.com/douma_edu/douma-algo/issues/I4WGCE
    }

    // 抽取成方法
    private void resize(int newCapacity) {
        // + 1 表示空余位置，用来区分队列是空还是满
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 循环 size 大小进行赋值
        for (int i = 0; i < size; i++) {
            // 从 head 位置开始进行赋值
            // % data.length 避免数组索引越界
            newData[i] = data[(i + head) % data.length];
        }
        // 修改队列引用
        data = newData;
        // head 和 tail 指针重新定位
        head = 0;
        tail = size;
    }

    // 出队
    @Override
    public E dequeue() { // O(1) 均摊
        //  出队判断队空
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, No Element for dequeue");
        }
        // 记录队首元素
        E res = data[head];
        // 设置为 null，释放内存
        data[head] = null;
        // 向右移动 head 指针
        // 注意：tail 和 head 都是往右移动
        head = (head + 1) % data.length;
        size--;

        // 出队进行缩容，避免时间复杂度震荡，size 设置 getCapacity() / 4
        if (size == getCapacity() / 4) {
            // getCapacity() / 2 不会变成 0，若要变成 0，则 getCapacity() / 4，就已经提前变成 0 了
            resize(getCapacity() / 2);
        }
        return res;
    }

    // 获取队首元素
    @Override
    public E getFront() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, No Element for dequeue");
        }
        return data[head];
    }

    // 输出打印
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // %d 对应后面的 size
        // 必须 String.format 这样定义
        sb.append(String.format("Queue：size = %d, capacity = %d ", size, getCapacity()));
        sb.append(" 队首 [");
        // 按照队首到队尾的顺序将队列中元素进行依次打印
        // i = (i + 1) % data.length 避免越界
        // 假设 data.length = 5，i 的最大索引是 4，4 + 1 则要返回 0 位置
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            // head 和 tail 不为同一个位置，则加 ','的
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
