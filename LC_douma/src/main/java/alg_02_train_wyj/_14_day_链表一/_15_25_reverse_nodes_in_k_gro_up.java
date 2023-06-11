package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:08
 * @Version 1.0
 */
public class _15_25_reverse_nodes_in_k_gro_up {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        ListNode first = head;
        ListNode last = first;
        ListNode next;
        while (first != null) {
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                if (last == null) return dummyNode.next;
            }
            next = last.next;
            last.next = null;

            reverse(first);

            prev.next = last;
            first.next = next;

            prev = first;
            first = next;
            last = first;
        }
        return dummyNode.next;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
