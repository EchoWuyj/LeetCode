package alg_01_ds_wyj._01_line._02_linkedlist;

import java.util.NoSuchElementException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 17:12
 * @Version 1.0
 */
public class DoubleLinkedList<E> {
    private class Node {
        E e;
        Node pre;
        Node next;

        public Node(E e, Node pre, Node next) {
            this.e = e;
            this.pre = pre;
            this.next = next;
        }

        public Node(E e) {
            this(e, null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node first;
    private Node last;
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
    public E get(int index) {
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        return node.e;
    }

    private Node node(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node cur = null;
        if (index < size / 2) {
            cur = first;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = last;
            for (int i = 0; i < size - index - 1; i++) {
                cur = cur.pre;
            }
        }
        return cur;
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
     */
    public void set(int index, E e) {
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
    public void addFirst(E e) {
        Node newNode = new Node(e);
        if (first == null) {
            last = newNode;
        } else {
            newNode.next = first;
            first.pre = newNode;
        }
        first = newNode;
        size++;
    }

    /**
     * 往链表尾巴插入新节点
     *
     * @param e
     */
    public void addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            first = newNode;
        } else {
            newNode.pre = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * 往指定索引位置插入节点
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        if (index == size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        } else {
            Node oldNode = node(index);
            Node pre = oldNode.pre;
            Node newNode = new Node(e, pre, oldNode);
            oldNode.pre = newNode;
            pre.next = newNode;
            size++;
        }
    }

    /**
     * 删除头节点
     *
     * @return
     */
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        E e = first.e;
        Node next = first.next;
        if (next == null) {
            first = null;
            last = null;
        } else {
            first.next = null;
            next.pre = null;
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
    public E removeLast() {

        if (last == null) {
            throw new NoSuchElementException();
        }
        E e = last.e;
        Node pre = last.pre;
        if (last == null) {
            last = null;
            first = null;
        } else {
            pre.next = null;
            last.pre = null;
            last = pre;
        }
        size--;
        return e;
    }

    /**
     * 删除中间节点
     *
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node delNode = node(index);
            E e = delNode.e;

            Node pre = delNode.pre;
            Node next = delNode.next;

            pre.next = next;
            next.pre = pre;

            delNode.next = null;
            delNode.pre = null;
            size--;
            return e;
        }
    }
}
