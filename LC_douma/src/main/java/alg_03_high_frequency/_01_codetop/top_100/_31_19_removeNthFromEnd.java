package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:46
 * @Version 1.0
 */
public class _31_19_removeNthFromEnd {

    // 删除链表的倒数第 N 个结点
    // 快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // slow 和 fast 都是从 dummy 位置开始，因为可能涉及删除 head 节点
        // 若 slow 和 fast 都是 head 位置开始，则无法将 head 节点删除
        ListNode slow = dummy;
        ListNode fast = dummy;

        // slow 和 fast 都是从 dummy 开始，则 slow 中点是靠右
        // 若 fast 先走一步，slow 中点靠左

        // 执行 n+1 次循环
        // 保证 fast 和 slow 之间相距 n+1 个节点
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        // fast 不断移动，直到 fast 移动到末尾
        while (fast != null) {
            // fast 和 slow 都往后移动
            fast = fast.next;
            slow = slow.next;
        }

        // slow 节点的 next 节点为待删除节点
        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        // 返回头节点
        return dummy.next;
    }
}
