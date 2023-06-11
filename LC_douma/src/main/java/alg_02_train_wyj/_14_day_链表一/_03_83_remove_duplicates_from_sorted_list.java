package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 18:54
 * @Version 1.0
 */
public class _03_83_remove_duplicates_from_sorted_list {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == prev.val) {
                prev.next = cur.next;
                cur.next = null;
            } else {
                prev = cur;
            }
            cur = prev.next;
        }
        return head;
    }
}
