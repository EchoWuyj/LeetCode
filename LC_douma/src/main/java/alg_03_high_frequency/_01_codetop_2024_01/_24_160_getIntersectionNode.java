package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:40
 * @Version 1.0
 */
public class _24_160_getIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // 存储节点
        Set<ListNode> set = new HashSet<>();

        // 遍历链表 headA
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        // 遍历链表 headB
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        // 返回 null
        return null;
    }
}
