package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-11-21 15:12
 * @Version 1.0
 */
public class _08_148_SortList2 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        for (int step = 1; step < count; step <<= 1) {
            ListNode prev = dummyNode;
            ListNode curr = dummyNode.next;
            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, step);
                curr = split(right, step);
                prev = merge(left, right, prev);
            }
        }
        return dummyNode.next;
    }

    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left == null) cur.next = right;
        if (right == null) cur.next = left;

        while (cur.next != null) cur = cur.next;
        return cur;
    }

    private ListNode split(ListNode node, int step) {
        if (node == null) return null;
        for (int i = 1; i < step; i++) {
            if (node.next != null) node = node.next;
        }
        ListNode right = node.next;
        node.next = null;
        return right;
    }
}
