package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 11:11
 * @Version 1.0
 */
public class _11_61_rotate_list1 {
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (k % len == 0) return head;
        k %= len;
        ListNode reverseListHead = reverse(head);

        ListNode kthNode = reverseListHead;
        for (int i = 0; i < k - 1; i++) {
            kthNode = kthNode.next;
        }
        ListNode restList = kthNode.next;
        kthNode.next = null;

        ListNode returnHead = reverse(reverseListHead);
        reverseListHead.next = reverse(restList);
        return returnHead;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}
