package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 11:42
 * @Version 1.0
 */
public class _07_147_InsertionSortList {

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val >= prev.val) {
                prev = cur;
                cur = cur.next;
            } else {
                ListNode p = dummyNode;
                while (p.next != null && p.next.val < cur.val) {
                    p = p.next;
                }
                prev.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                cur = prev.next;
            }
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{4, 2, 1, 3});
        System.out.println(insertionSortList(head));
    }
}
