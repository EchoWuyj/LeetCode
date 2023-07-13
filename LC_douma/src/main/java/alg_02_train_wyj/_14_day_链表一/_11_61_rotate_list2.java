package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 20:32
 * @Version 1.0
 */
public class _11_61_rotate_list2 {
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode lastNode = head;
        while (lastNode.next != null) {
            len++;
            lastNode = lastNode.next;
        }
        len++;

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
