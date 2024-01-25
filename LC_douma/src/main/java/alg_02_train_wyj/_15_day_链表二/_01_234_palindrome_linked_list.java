package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:41
 * @Version 1.0
 */
public class _01_234_palindrome_linked_list {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        right = reverse(right);

        while (right != null) {
            if (right.val != head.val) return false;
            right = right.next;
            head = head.next;
        }

        return true;
    }

    public ListNode reverse(ListNode head) {
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
}
