package alg_01_ds_dm._01_line._02_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 20:44
 * @Version 1.0
 */
public class LinkedList<E> {
    // 定义节点
    // Node 为 LinkedList 内部私有的类，外部没法访问
    private class Node {
        // 定义泛型被 Node 节点包裹
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            // KeyPoint 调用已有构造方法，避免重复
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 虚拟头节点
    private final Node dummyHead;
    // 长度
    private int size;

    public LinkedList() {
        // 存的值可以不设置
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 查询指定索引的节点的值
     *
     * @param index
     * @return
     */
    // 时间复杂度:O(n)
    public E get(int index) {
        // 检查 index 的合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");
        // 从头节点依次遍历
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    // 时间复杂度:O(1)
    public E getFirst() {
        return get(0);
    }

    // 时间复杂度:O(n)
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改指定索引的节点元素
     *
     * @param index
     * @param e
     */
    // 时间复杂度:O(n)
    public void set(int index, E e) {
        // 检查 index 的合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");

        Node curr = dummyHead.next;
        // KeyPoint 这种结构下，i < index 则循环 index 次
        //      因为 for 循环都是从头节点开始的，也就是索引 0 位置开始
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.e = e;
    }

    /**
     * 在指定索引的位置插入新的节点
     *
     * @param index 需要插入的位置
     * @param e     需要插入的数据
     */
    // 时间复杂度:O(n)
    public void add(int index, E e) {
        // 检查 index 的合法性
        // size 位置可以插入，若 index == size，则是链表最后一个元素的后一个位置
        // 因为链表是动态数据结构，没有固定大小，不像数组有 length，链表的 size 可以一直增大
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        Node prev = dummyHead;
        // 因为 prev 在 dummyHead 位置，for 循环执行 index 次后
        // pre 正好在 index 节点的前一个节点位置
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // 1 先操作 node 的 next 指针指向 prev.next
        // 2 再操作 prev 的 next 指针指向 node
        prev.next = new Node(e, prev.next);

        size++;
    }

    /**
     * 在链表表头新增节点
     *
     * @param e 新增节点需要存储的数据
     */
    // 时间复杂度:O(1)
    public void addFirst(E e) {
        add(0, e);
    }

    // 时间复杂度:O(n)
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 删除指定索引的节点，并返回删除的节点的值
     * KeyPoint 一般删除都是要返回被删除的元素，故返回值不为 void
     *
     * @param index
     * @return
     */
    // 时间复杂度:O(n)
    public E remove(int index) {
        // 检查 index 的合法性
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // 使用 delNode 保存被删除的节点
        Node delNode = prev.next;
        prev.next = delNode.next;
        // KeyPoint 被删除的节点要断绝和原链表的指针联系，不然存在 bug
        delNode.next = null;

        size--;

        return delNode.e;
    }

    /**
     * 删除链表的头节点
     *
     * @return
     */
    // 时间复杂度:O(1)
    public E removeFirst() {
        return remove(0);
    }

    // 时间复杂度:O(n)
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除第一个值等于 e 的节点
     *
     * @param e
     */
    public void removeElement(E e) {
        // 链表为空
        if (dummyHead.next == null)
            throw new IllegalArgumentException("removeElement failed, LinkedList is Empty");

        // 既然要删除值 e 的节点，则得使用 prev 记录该节点的前一个几点
        Node prev = dummyHead;
        Node curr = dummyHead.next;

        // 无法知道 e 值的节点在什么位置，即不好确定 for 循环的次数
        // 故使用 while 依次进行遍历，直到找到为止，否则不删除
        while (curr != null) {
            if (curr.e.equals(e)) {
                break;
            }
            // while循环中一定要不断移动指针，否则是死循环
            prev = curr;
            curr = curr.next;
        }

        // KeyPoint bug 修复，需要先判断 curr，如果 curr 为 null 的话，说明链表中不存在值等于 e 的节点，执行删除操作
        if (curr != null) {
            prev.next = curr.next;
            // 被删除节点的next指针置为null
            curr.next = null;
        }
    }

    /**
     * 判断链表中是否存在指定元素
     *
     * @param e
     * @return
     */
    // 时间复杂度:O(n)
    public boolean contains(E e) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.e.equals(e)) {
                return true;
            }
            // cur指针后移
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;
        // 遍历过程按照指定格式进行输出
        while (curr != null) {
            sb.append(curr + " => ");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
