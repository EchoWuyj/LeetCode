package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:45
 * @Version 1.0
 */
public class _77_234_isPalindrome {

    // 回文链表
    // 直接模拟
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        // 找中点
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 拆分链表
        ListNode right = slow.next;
        slow.next = null;

        //  _ _ _  _ _
        //  ↑      ↑
        // left  right
        // 左一半链表长，右一半链表短
        ListNode left = head;
        right = reverse(right);

        // 以短链表为标准
        while (right != null) {
            if (left.val != right.val) return false;
            // 指针后移
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
