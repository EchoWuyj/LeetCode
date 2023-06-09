package alg_01_ds_dm._01_line._02_linkedlist;

import java.util.NoSuchElementException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 21:48
 * @Version 1.0
 */
public class DoubleLinkedList<E> {
    private class Node {
        E e;
        Node prev;
        Node next;

        public Node(Node prev, E e, Node next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(null, e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node first; // 头节点
    private Node last; // 尾节点
    private int size;

    public DoubleLinkedList() {
        first = last = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 找到指定索引 index 所在的节点的元素值
     *
     * @param index
     * @return
     */
    // 时间复杂度是 O(n)
    public E get(int index) {
        // 通过 index 找到 node
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        return node.e;
    }

    /**
     * 查找指定 index 对应节点
     * 后面多个代码使用到，故将其抽取出来
     *
     * @param index
     * @return
     */
    // 减少了一半查询 n/2，查询效率提高了，但是时间复杂度是 O(n)
    private Node node(int index) {
        if (index < 0 || index >= size)
            return null;

        Node curr = null;
        // 如果 index 小于链表长度的一半，则从 first 开始遍历查找
        if (index < size / 2) {
            curr = first;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else { // 如果 index 大于等于链表长度的一半，则从 last 开始遍历查找
            curr = last;
            // KeyPoint 因为索引是从 0 开始的，故 size - index 少一位，故需要再减 1
            for (int i = 0; i < size - index - 1; i++) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    // 时间复杂度是 O(1)
    public Node getFirst() {
        return first;
    }

    // 时间复杂度是 O(1)
    public Node getLast() {
        return last;
    }

    /**
     * 修改指定 index 位置节点的值
     *
     * @param index
     * @param e
     */
    // 时间复杂度是 O(n)
    public void set(int index, E e) {
        // 先找到需要修改的节点
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        node.e = e;
    }

    /**
     * 往链表的表头插入节点
     *
     * @param e
     */
    // 时间复杂度是 O(1)
    public void addFirst(E e) {
        Node newNode = new Node(e);
        // 分情况讨论
        if (first == null) {
            // 如果头节点为空，说明链表中没有一个节点
            // 那么新插入的节点既是头节点，又是尾节点
            last = newNode;
        } else {
            // 将新节点作为头节点
            newNode.next = first;
            first.prev = newNode;
        }
        // 两种情况都是要更新 first
        // 头指针移动到新的头节点
        first = newNode;
        // 维护size
        size++;
    }

    /**
     * 往链表尾巴插入新节点
     *
     * @param e
     */
    // 时间复杂度是 O(1)
    public void addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            // 如果尾节点为空，说明链表中没有一个节点
            // 那么新插入的节点既是头节点，又是尾节点
            first = newNode;
        } else {
            // 将新节点作为尾节点
            newNode.prev = last;
            last.next = newNode;
        }
        // 两种情况都是要更新 last
        last = newNode;
        size++;
    }

    /**
     * 往指定索引位置插入节点
     *
     * @param index
     * @param e
     */
    // 时间复杂度是 O(n)
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        // 对插入位置 index 进行判断
        // 1 头节点
        if (index == size) {
            addLast(e);
            // 2 尾节点
        } else if (index == 0) {
            addFirst(e);
            // 3 一般位置
        } else {

            // 3.1 找到要插入的位置，并记住这个位置的节点
            // oldNode 指的是原来 index 位置的节点
            Node oldNode = node(index);
            Node prev = oldNode.prev;

            // KeyPoint 1 newNode 的前后指针设置
            // 3.2 新建节点，将它的 next 指向 oldNode，将它的 prev 指向 oldNode.prev
            Node newNode = new Node(prev, e, oldNode);

            // KeyPoint 2 newNode 前后节点指针设置
            // 3.3 将新建节点设置为 oldNode 的 prev
            oldNode.prev = newNode;

            // 3.4 将新建节点设置 oldNode 之前的 prev 的 next
            prev.next = newNode;

            size++;
        }
    }

    /**
     * 删除头节点
     *
     * @return
     */
    // 时间复杂度是 O(1)
    public E removeFirst() {
        // 没有头节点，直接抛出异常
        if (first == null) {
            throw new NoSuchElementException();
        }

        // 将删除节点的值保存，后面进行返回
        E e = first.e;
        // 拿到头节点的下一个节点
        Node next = first.next;
        // 关键：分情况讨论
        // 1 如果 next 为空，说明整个链表只有一个节点
        if (next == null) {
            first = null;
            last = null;
            // 2 链表不止一个节点
        } else {
            // 将头节点从链表中断开
            first.next = null;
            next.prev = null;
            // 更新 first 指针，将 next 设置为头节点
            first = next;
        }
        size--;
        return e;
    }

    /**
     * 删除尾节点
     *
     * @return
     */
    // 时间复杂度是 O(1)
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        E e = last.e;
        // 拿到尾节点的前一个节点
        Node prev = last.prev;
        // 如果 prev 为空，说明整个链表只有一个节点
        if (prev == null) {
            last = null;
            first = null;
        } else {
            // 将尾节点从链表中断开
            // 1 last 的 pre
            last.prev = null;
            // 2 pre 的 next
            prev.next = null;
            // 将 prev 设置为尾节点
            last = prev;
        }
        size--;
        return e;
    }

    /**
     * 删除中间节点
     *
     * @param index
     * @return
     */
    // 时间复杂度是 O(n)
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        // 1 找到要删除的节点
        Node delNode = node(index);
        E e = delNode.e;

        // 2 记住要删除节点的 prev 和 next 节点
        Node prev = delNode.prev;
        Node next = delNode.next;

        // 3 将删除节点的前后节点联系起来
        prev.next = next;
        next.prev = prev;

        // 4 将删除节点从链表中断开
        delNode.next = null;
        delNode.prev = null;

        size--;
        return e;
    }
}
