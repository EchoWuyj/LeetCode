package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 16:44
 * @Version 1.0
 */
public class _14_24_swap_nodes_in_pairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode first = head;
        ListNode second = head.next;
        ListNode prev = dummyNode;
        ListNode next;
        while (second != null) {
            next = second.next;

            first.next = next;
            second.next = prev.next;
            prev.next = second;

            System.out.println(dummyNode.next);

            prev = first;
            first = next;
            if (first == null) break;
            second = first.next;
            System.out.println(dummyNode.next);
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        System.out.println(swapPairs(head));
    }
}
