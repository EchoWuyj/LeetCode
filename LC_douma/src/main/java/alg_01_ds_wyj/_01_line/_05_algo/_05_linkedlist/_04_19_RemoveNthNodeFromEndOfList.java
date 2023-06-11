package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 20:48
 * @Version 1.0
 */
public class _04_19_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        ListNode prev = dummyNode;
        for (int i = 0; i < count - n; i++) {
            prev = prev.next;
        }

        ListNode delNode = prev.next;
        prev.next = delNode;
        delNode.next = null;
        return dummyNode.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode fast = dummyNode;
        ListNode slow = dummyNode;

        while (n >= 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;
        return dummyNode.next;
    }

    int count = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        head.next = removeNthFromEnd(head.next, n);
        count++;
        if (count == n) return head.next;
        return head;
    }
}
