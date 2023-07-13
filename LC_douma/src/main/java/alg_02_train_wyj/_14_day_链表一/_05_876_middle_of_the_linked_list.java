package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 11:27
 * @Version 1.0
 */
public class _05_876_middle_of_the_linked_list {

    public static ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        for (int i = 0; i < len / 2; i++) {
            head = head.next;
        }
        return head;
    }

    public static ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
