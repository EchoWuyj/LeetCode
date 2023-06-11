package alg_01_新手班_wyj.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-09 13:32
 * @Version 1.0
 */
public class Code06_MergeTwoSortedLinkedList {
    public static class ListNode {
        private int val;
        private ListNode next;
    }

    public ListNode mergeTowSortedList(ListNode head1, ListNode head2) {

        if (head1 == null || head2 == null) {
            return (head1 == null) ? head2 : head1;
        }

        ListNode head = (head1.val <= head2.val) ? head1 : head2;

        ListNode cur1 = head.next;
        ListNode cur2 = (head == head1) ? head2 : head1;

        ListNode pre = head;

        // 同时不为null
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }

            pre = pre.next;
        }

        pre.next = (cur1 != null) ? cur1 : cur2;
        return head;
    }
}
