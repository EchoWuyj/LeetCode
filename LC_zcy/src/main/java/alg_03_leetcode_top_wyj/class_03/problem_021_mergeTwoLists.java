package alg_03_leetcode_top_wyj.class_03;

/**
 * @Author Wuyj
 * @DateTime 2023-02-23 12:00
 * @Version 1.0
 */
public class problem_021_mergeTwoLists {
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                pre.next = head1;
                head1 = head1.next;
            } else {
                pre.next = head2;
                head2 = head2.next;
            }
            pre = pre.next;
        }

        if (head1 != null) {
            pre.next = head1;
        }

        if (head2 != null) {
            pre.next = head2;
        }

        return dummy.next;
    }
}
