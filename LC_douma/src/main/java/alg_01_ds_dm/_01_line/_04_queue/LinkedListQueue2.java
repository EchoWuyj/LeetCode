package alg_01_ds_dm._01_line._04_queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 14:50
 * @Version 1.0
 */
public class LinkedListQueue2<E> implements Queue<E> {

    // LinkedListQueue2 链表中节点定义
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

    // LinkedListQueue2 指针定义
    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue2() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) { // O(1)
        Node newNode = new Node(e);
        // KeyPoint 执行一个操作，为了保证其健壮性，需要考虑特殊情况的判断
        // 1 队列为空
        if (tail == null) {
            tail = newNode;
            head = tail;
            // 2 队列不为空
        } else {

            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() { // O(1)
        // 出队前考虑队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, no element");
        }
        // 从队首出队
        Node delNode = head;
        head = head.next;
        delNode.next = null;

        // KeyPoint 删除后特殊情况
        // 删除一个元素之后，队列中没有元素，此时 head 和 tail 应该指向同一个位置
        if (head == null) {
            tail = null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("getFront error, no element");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue：队首 [");
        // 通过指针遍历的方式进行打印输出
        Node curr = head;
        while (curr != null) {
            res.append(curr + " -> ");
            curr = curr.next;
        }
        res.append("null]");
        return res.toString();
    }
}
