package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 19:37
 * @Version 1.0
 */
public class _02_206_ReverseLinkedList {
    public ListNode reverseList1(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        ListNode prev = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head.next == null) return head;
        ListNode tail = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }
}
