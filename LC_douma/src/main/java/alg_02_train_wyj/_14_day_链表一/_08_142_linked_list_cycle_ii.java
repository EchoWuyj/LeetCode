package alg_02_train_wyj._14_day_链表一;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 20:25
 * @Version 1.0
 */
public class _08_142_linked_list_cycle_ii {
    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) return head;
            visited.add(head);
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                ListNode p = head;
                while (p != slow) {
                    slow = slow.next;
                    p = p.next;
                }
                return p;
            }
        }
        return null;
    }
}
