package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-11-19 14:47
 * @Version 1.0
 */
public class _补充_143_reorder_list {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode mid = getMid(head);
        ListNode list1 = head;
        ListNode list2 = mid.next;
        list2 = reverse(list2);
        mid.next = null;
        merge(list1, list2);
    }

    public ListNode getMid(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode node) {
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

    public void merge(ListNode list1, ListNode list2) {
        ListNode cur1;
        ListNode cur2;
        while (list1 != null && list2 != null) {
            cur1 = list1.next;
            cur2 = list2.next;

            list1.next = list2;
            list1 = cur1;

            list2.next = list1;
            list2 = cur2;
        }
    }
}
