package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 18:24
 * @Version 1.0
 */
public class _02_237_delete_node_in_a_linked_list {
    public void deleteNode1(ListNode node) {
        ListNode prev = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null) {
                cur.val = next.val;
            } else {
                if (prev != null) prev.next = null;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    public void deleteNode2(ListNode node) {
        node.val = node.next.val;
        ListNode next = node.next;
        node.next = next.next;
        next.next = null;
    }
}
