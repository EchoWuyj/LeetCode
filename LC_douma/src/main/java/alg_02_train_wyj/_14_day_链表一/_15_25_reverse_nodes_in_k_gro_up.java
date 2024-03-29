package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:08
 * @Version 1.0
 */
public class _15_25_reverse_nodes_in_k_gro_up {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode first = head;
        ListNode last = first;
        ListNode next = first;

        while (next != null) {
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                if (last == null) return dummy.next;
            }

            if (last != null) {
                next = last.next;
            }

            pre.next = null;
            last.next = null;

            reverse(first);
            pre.next = last;
            first.next = next;

            pre = first;
            if (next != null) first = next;
            last = first;
            next = first;
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        reverseKGroup(head, 2);
    }

    private static ListNode createLinkedList(int[] values) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummyHead.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
