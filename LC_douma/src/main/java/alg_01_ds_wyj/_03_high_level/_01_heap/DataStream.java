package alg_01_ds_wyj._03_high_level._01_heap;

import org.w3c.dom.html.HTMLDOMImplementation;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 18:27
 * @Version 1.0
 */
public class DataStream {
    private class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node dummyNode;

    public DataStream() {
        this.dummyNode = new Node(-1);
    }

    public void add(int val) {
        Node newNode = new Node(val);
        newNode.next = dummyNode.next;
        dummyNode.next = newNode;
    }

    public int removeMax() {
        if (dummyNode.next == null) {
            throw new RuntimeException("removeMax 失败，因为数据流中没有元素");
        }
        Node cur = dummyNode.next;
        Node maxValueNode = cur;
        while (cur != null) {
            if (cur.val > maxValueNode.val) {
                maxValueNode = cur;
            }
            cur = cur.next;
        }

        cur = dummyNode.next;
        Node maxValueNodePre = dummyNode;
        while (cur != null) {
            if (cur == maxValueNode) {
                break;
            }
            maxValueNodePre = cur;
            cur = cur.next;
        }

        maxValueNodePre.next = maxValueNode.next;
        maxValueNode.next = null;
        return maxValueNode.val;
    }
}
