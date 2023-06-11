package alg_03_leetcode_top_wyj.class_01;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 18:59
 * @Version 1.0
 */
public class problem_002_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int res = 0;

        ListNode l1 = head1;
        ListNode l2 = head2;
        ListNode cur = null;
        ListNode pre = null;

        while (l1 != null || l2 != null) {
            n1 = l1 == null ? 0 : l1.val;
            n2 = l2 == null ? 0 : l2.val;
            res = n1 + n2 + ca;
            ca = res / 10;
            pre = cur;
            cur = new ListNode(res % 10);
            cur.next = pre;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (ca == 1) {
            pre = cur;
            cur = new ListNode(1);
            cur.next = pre;
        }
        return reverseList(cur);
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
