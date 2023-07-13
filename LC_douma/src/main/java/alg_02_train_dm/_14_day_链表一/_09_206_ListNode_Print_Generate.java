package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-13 16:21
 * @Version 1.0
 */
public class _09_206_ListNode_Print_Generate {

    public static void main(String[] args) {
        ListNode head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        printLinkedList(head);
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
