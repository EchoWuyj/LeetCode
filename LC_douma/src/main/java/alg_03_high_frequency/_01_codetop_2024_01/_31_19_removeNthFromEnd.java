package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 19:46
 * @Version 1.0
 */
public class _31_19_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // slow 和 fast 都是从 dummy 开始，则slow 中点是靠右
        // 若fast 先走一步，slow 中点靠左
        ListNode slow = dummy;
        ListNode fast = dummy;

        // 执行 n+1 次循环
        // 保证 fast 和 slow 之间相距 n+1 个节点
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        // fast 移动末尾
        while (fast != null) {
            // fast 和 slow 都往后移动
            fast = fast.next;
            slow = slow.next;
        }

        // slow 节点 next 节点为待删除节点
        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        // 返回头节点
        return dummy.next;
    }
}
