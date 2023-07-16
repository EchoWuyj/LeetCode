package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 21:29
 * @Version 1.0
 */
public class _10_148_sort_list2 {

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        for (int step = 1; step < len; step <<= 1) {
            ListNode pre = dummy;
            cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(left, step);
                cur = cut(right, step);
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }

    private static ListNode cut(ListNode node, int step) {
        if (node == null) return null;
        for (int i = 1; i < step; i++) {
            if (node.next != null) node = node.next;
        }
        ListNode right = node.next;
        node.next = null;
        return right;
    }

    private static ListNode merge(ListNode left, ListNode right, ListNode pre) {
        ListNode cur = pre;
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
        if (left != null) cur.next = left;
        if (right != null) cur.next = right;
        printLinkedList(pre);
        while (cur.next != null) cur = cur.next;
        return cur;
    }

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{4, 2, 1, 3});
        ListNode res = sortList(head);
        printLinkedList(res);
    }

    private static ListNode createLinkedList(int[] values) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummyHead.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
