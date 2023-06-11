package alg_01_ds_dm._03_high_level._01_heap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 13:54
 * @Version 1.0
 */
public class DataStream {
    // 单链表实现
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

    // O(1)
    // 头插法，不去比较大小，直接插入
    public void add(int val) {
        Node newNode = new Node(val);
        // 表头插入新节点
        newNode.next = dummyNode.next;
        dummyNode.next = newNode;
    }

    // O(n)
    // 1 遍历一遍链表找到最大值节点
    // 2 遍历一遍链表找到最大值节点的前一个节点，修改指针从而将其删除
    // KEY 适用：新增多，但是删除(查询)比较小
    public int removeMax() {
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax 失败，因为数据流中没有元素");

        // 遍历一遍链表，找到最大值所在的节点
        // 最大值在链表中的位置是不确定的，只有遍历一遍链表才能找到最大值
        Node curr = dummyNode.next;
        // 记录最大值节点的指针
        Node maxValueNode = curr;
        while (curr != null) {
            if (curr.val > maxValueNode.val) {
                maxValueNode = curr;
            }
            curr = curr.next;
        }

        // 找到最大值节点的前一个节点
        curr = dummyNode.next;
        // maxValueNodePrev 一开始就在 dummyNode 位置
        Node maxValueNodePrev = dummyNode;
        while (curr != null) {
            if (curr == maxValueNode) {
                break;
            }
            maxValueNodePrev = curr;
            curr = curr.next;
        }

        // 删除最大值所在的节点
        maxValueNodePrev.next = maxValueNode.next;
        maxValueNode.next = null;
        return maxValueNode.val;
    }
}
