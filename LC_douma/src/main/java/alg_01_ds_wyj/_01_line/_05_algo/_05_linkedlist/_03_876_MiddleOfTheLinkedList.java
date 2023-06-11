package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 20:16
 * @Version 1.0
 */
public class _03_876_MiddleOfTheLinkedList {
    public ListNode middleNode1(ListNode head) {
        if (head == null) return null;
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        curr = head;
        for (int i = 0; i < (count / 2) + 1; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public ListNode middleNode2(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
