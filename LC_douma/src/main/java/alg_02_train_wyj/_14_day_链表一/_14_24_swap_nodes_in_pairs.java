package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 16:44
 * @Version 1.0
 */
public class _14_24_swap_nodes_in_pairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode first = head;
        ListNode second = first.next;
        ListNode next = second.next;

        while (second != null) {
            first.next = next;
            second.next = pre.next;
            pre.next = second;

            pre = first;
            first = next;
            if (next == null) break;

            second = next.next;
            if (second != null) next = second.next;
        }
        return dummy.next;
    }
}
