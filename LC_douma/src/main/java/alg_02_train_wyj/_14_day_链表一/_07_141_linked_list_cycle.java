package alg_02_train_wyj._14_day_链表一;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 20:06
 * @Version 1.0
 */
public class _07_141_linked_list_cycle {

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
