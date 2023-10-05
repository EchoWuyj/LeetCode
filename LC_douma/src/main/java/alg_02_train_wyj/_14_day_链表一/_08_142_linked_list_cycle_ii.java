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
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) return head;
            set.add(head);
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode p = head;
                while (slow != p) {
                    slow = slow.next;
                    p = p.next;
                }
                return p;
            }
        }
        return null;
    }
}
