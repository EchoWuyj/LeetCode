package alg_01_ds_wyj._03_high_level._01_heap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 18:49
 * @Version 1.0
 */
public class DataStream1 {
    private class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node dummyNode;

    public DataStream1() {
        this.dummyNode = new Node(-1);
    }

    // O(n)
    public void add(int val) {
        Node prev = dummyNode;
        Node cur = dummyNode.next;
        while (cur != null) {
            if (cur.val < val) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        Node newNode = new Node(val);
        newNode.next = cur;
        prev.next = newNode;
    }

    public int removeMax() {
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax 失败，因为数据流中没有元素");

        int res = dummyNode.next.val;
        Node head = dummyNode.next;
        dummyNode.next = head.next;
        head.next = null;
        return res;
    }
}
