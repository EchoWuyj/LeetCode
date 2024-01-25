package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 12:48
 * @Version 1.0
 */
public class _06_19_remove_nth_node_from_end_of_list {
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int len = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        ListNode pre = dummy;
        for (int i = 0; i < len - n; i++) {
            pre = pre.next;
        }
        ListNode next = pre.next;
        pre.next = next.next;
        next.next = null;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;
        return dummy.next;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        return process(head, n, 0);
    }

    public ListNode process(ListNode head, int n, int count) {
        if (head == null) return null;
        head.next = process(head.next, n, count + 1);
        if (count == n) return head.next;
        return head;
    }
}
