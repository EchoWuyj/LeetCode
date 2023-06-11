package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 19:36
 * @Version 1.0
 */
public class _04_82_remove_duplicates_from_sorted_list_ii {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head;
        ListNode pre = dummyNode;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                do {
                    cur = cur.next;
                } while (cur.next != null && cur.val == cur.next.val);
                pre.next = cur.next;
                cur.next = null;
            } else {
                pre = cur;
            }
            cur = pre.next;
        }
        return dummyNode.next;
    }
}
