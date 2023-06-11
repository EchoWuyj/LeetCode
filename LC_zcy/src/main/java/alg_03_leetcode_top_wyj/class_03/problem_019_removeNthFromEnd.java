package alg_03_leetcode_top_wyj.class_03;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 20:32
 * @Version 1.0
 */
public class problem_019_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode del = null;
        boolean flag = false;
        while (cur != null) {
            n--;
            if (n <= 0) {
                if (n == 0) {
                    flag = true;
                } else if (n == -1) {
                    del = head;
                } else {
                    del = del.next;
                }
            }
            cur = cur.next;
        }

        if (!flag) {
            return head;
        }
        if (del == null) {
            return head.next;
        }
        del.next = del.next.next;
        return head;
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