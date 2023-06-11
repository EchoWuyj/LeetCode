package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 11:11
 * @Version 1.0
 */
public class _11_61_rotate_list {
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        if (k % len == 0) return head;
        k %= len;

        ListNode reverseHead = reverse(head);
        curr = reverseHead;
        for (int i = 0; i < k - 1; i++) {
            curr = curr.next;
        }
        ListNode rightList = curr.next;
        curr.next = null;

        ListNode prevHead = reverse(reverseHead);
        reverseHead.next = reverse(rightList);
        return prevHead;
    }

    public ListNode reverse(ListNode head) {
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

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 1;
        ListNode lastNode = head;
        while (lastNode.next != null) {
            len++;
            lastNode = lastNode.next;
        }

        if (k % len == 0) return head;
        k %= len;

        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        lastNode.next = head;
        return newHead;
    }
}
