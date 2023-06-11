package alg_01_ds_dm._03_high_level._02_set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 14:58
 * @Version 1.0
 */

// KeyPoint 拉链法 => 哈希冲突
public class HashSet3<E> implements Set<E> {

    // 定义的含有泛型 E 的 Node 节点，在使用 Node 时，最好将 <E> 加上
    private class Node<E> {
        E e;
        Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    // 链表节点构成的数组
    private Node<E>[] data;
    private int size;

    // 装载因子
    // 1 数组中空余的位置不多，此时哈希冲突的概率大大提高，此时需要不断往后遍历
    //   找第一个空位，则可能需要找很多空位，从而导致 HashSet 的性能变差
    // 2 由于哈希冲突变多了，导致每个元素挂载的链表长度很长，又导致 HashSet 性能很差
    private double loadFactor = 0.75;

    public HashSet3() {
        data = new Node[10];
        this.size = 0;
    }

    public HashSet3(int loadFactor) {
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
    public void add(E e) { // 最好 O(1)，最差 O(n)
        int index = hash(e, data.length);
        if (data[index] == null) {
            data[index] = new Node<>(e);
        } else {
            // 插入元素操作，需要记住插入元素的前一个节点
            Node<E> prev = null;
            Node<E> curr = data[index];
            // 遍历链表
            while (curr != null) {
                // e 已经存在，直接 return
                if (e.equals(curr.e)) {
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
            // 跳出 while 循环，cur 为 null，prev 在最后一个节点位置
            prev.next = new Node<>(e);
        }
        size++;

        // 扩容
        if (size >= data.length * loadFactor) {
            resize(2 * data.length);
        }
    }

    private void resize(int newCapacity) {
        Node<E>[] newData = new Node[newCapacity];
        for (int i = 0; i < data.length; i++) {
            Node<E> curr = data[i];
            while (curr != null) {
                E e = curr.e;
                int newIndex = hash(e, newCapacity);
                Node<E> head = newData[newIndex];
                newData[newIndex] = new Node<E>(e);
                // head 不为 null，直接插入链表头部即可，链表中的元素都是不相同的
                if (head != null) {
                    newData[newIndex].next = head;
                }
                // bug 修复：curr 需要往前移动
                curr = curr.next;
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) {
        int index = hash(e, data.length);
        // HashSet 中没有该元素 e
        if (data[index] == null) {
            return;
        }

        // 遍历链表，删除操作，需要 prev 指针
        Node<E> prev = null;
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                if (prev == null) {
                    // 删除头节点
                    data[index] = curr.next;
                } else {
                    // 删除非头节点
                    prev.next = curr.next;
                }
                curr.next = null;
                size--;
                // 删除之后直接退出循环
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) return false;
        // 必须遍历链表才能确定元素是否存在
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}
