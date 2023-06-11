package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 15:49
 * @Version 1.0
 */
public class _03_86_partition_list {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode smallHead = new ListNode(-1);
        ListNode largeHead = new ListNode(-1);
        ListNode small = smallHead;
        ListNode larger = largeHead;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                larger.next = head;
                larger = larger.next;
            }
            head = head.next;
        }
        larger.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }
}
