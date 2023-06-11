package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 17:48
 * @Version 1.0
 */
public class LinkedListCounter {
    public int count(ListNode head) {
        if (head == null) return 0;
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    public int countFor(ListNode head) {
        if (head == null) return 0;
        int count = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            count++;
        }
        return count;
    }

    public int countTarget(ListNode head, int target) {
        if (head == null) return 0;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            if (cur.val == target) count++;
            cur = cur.next;
        }
        return count;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 3, 4, 5});
        LinkedListCounter counter = new LinkedListCounter();
        System.out.println(counter.count(head));
        System.out.println(counter.countFor(head));
        System.out.println(counter.countTarget(head, 3));
    }
}
