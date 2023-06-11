package alg_01_ds_dm._03_high_level._01_heap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 14:11
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
    // 插入时按照逆序排列
    public void add(int val) {
        // 找到第一个比 val 小的节点
        Node prev = dummyNode;   // 前一个节点
        Node curr = dummyNode.next;    // 当前遍历节点
        while (curr != null) {
            if (curr.val < val) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        // 将元素添加到第一个比它小的节点的前面
        Node newNode = new Node(val);
        newNode.next = curr;
        prev.next = newNode;
    }

    // O(1)
    // 因为链表逆序排列，第一个元素就是最大值，直接删除即可
    // KeyPoint 适用：删除(查询)多，但是新增比较小
    public int removeMax() {
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax 失败，因为数据流中没有元素");

        // 保存需要返回的最大值 (头节点的值)
        int res = dummyNode.next.val;

        // 删除头节点
        Node head = dummyNode.next;
        dummyNode.next = head.next;
        head.next = null;

        return res;
    }
}
