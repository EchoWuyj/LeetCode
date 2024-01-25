package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _91_83_deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 避免和节点范围冲突
        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (pre.val == cur.val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
