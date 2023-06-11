package algorithm._05_linked_list.wyj;

import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 18:51
 * @Version 1.0
 */
public class LeetCode_141_HasCycle {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public boolean hasCycle1(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }

        HashSet<ListNode> set = new HashSet();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return false;
            } else {
                set.add(cur);
            }
            cur = cur.next;
        }
        return true;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
