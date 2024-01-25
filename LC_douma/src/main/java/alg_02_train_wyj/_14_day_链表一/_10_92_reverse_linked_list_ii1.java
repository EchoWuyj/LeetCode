package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 21:35
 * @Version 1.0
 */
public class _10_92_reverse_linked_list_ii1 {
    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode leftNode = pre.next;
        ListNode tmp = leftNode;
        for (int i = 0; i < right - left; i++) {
            leftNode = leftNode.next;
        }

        ListNode rightNode = leftNode;
        ListNode post = rightNode.next;
        pre.next = null;
        rightNode.next = null;

        ListNode reverseList = reverse(tmp);

        printLinkedList(reverseList);
        pre.next = reverseList;
        tmp.next = post;
        return dummy.next;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        ListNode list = createLinkedList(array);
        reverseBetween1(list, 2, 4);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
    }
}