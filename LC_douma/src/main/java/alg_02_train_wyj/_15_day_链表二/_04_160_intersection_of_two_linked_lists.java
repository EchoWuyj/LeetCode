package alg_02_train_wyj._15_day_链表二;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 16:17
 * @Version 1.0
 */
public class _04_160_intersection_of_two_linked_lists {
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode first = headA;
        ListNode second = headB;

        while (first != second) {
            first = (first == null) ? headB : first.next;
            second = (second == null) ? headA : second.next;
        }
        return first;
    }
}
