package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;



/**
 * @Author Wuyj
 * @DateTime 2023-05-01 14:58
 * @Version 1.0
 */
public class _08_148_SortList1 {

    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode leftNode = head;
        ListNode rightNode = slow.next;
        slow.next = null;

        ListNode sort1 = sortList1(leftNode);
        ListNode sort2 = sortList1(rightNode);

        return mergeList(sort1, sort2);
    }

    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
            }
        }

        if (list1 == null) cur.next = list2;
        if (list2 == null) cur.next = list1;

        return dummy.next;
    }
}
