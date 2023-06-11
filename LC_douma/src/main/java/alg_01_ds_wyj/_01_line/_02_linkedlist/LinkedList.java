package alg_01_ds_wyj._01_line._02_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 15:43
 * @Version 1.0
 */
public class LinkedList<E> {
    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
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

    private Node dummyHead;
    private int size;

    public LinkedList() {
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
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
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
    public void set(int index, E e) {
        // 检查 index 的合法性
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.e = e;
    }

    /**
     * 在指定索引的位置插入新的节点
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

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
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

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
        if (dummyHead.next == null)
            throw new IllegalArgumentException("removeElement failed, LinkedList is Empty");

        Node pre = dummyHead;
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        if (cur != null) {
            pre.next = cur.next;
            cur.next = null;
        }
    }

    /**
     * 判断链表中是否存在指定元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur + " => ");
            cur = cur.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
