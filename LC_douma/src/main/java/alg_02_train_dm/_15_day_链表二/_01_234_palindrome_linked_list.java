package alg_02_train_dm._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:35
 * @Version 1.0
 */
public class _01_234_palindrome_linked_list {
      /*
         234. 回文链表
        请判断一个链表是否为回文链表。

        输入: 1->2->2->1
        输出: true

        输入: 1->2->3->2->1
        输出: true

        进阶：
        你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
        KeyPoint 不能将链表放到数组中，判断数组是否能是回文
     */

    // 时间复杂度 O(n) 两次遍历，一次找中点和反转，一次比较
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 找中点(靠左)
        ListNode slow = head, fast = head.next;
        // KeyPoint 严格遵守判空条件逻辑递进性
        // fast.next != null && fast != null × 若 fast == null，则 fast.next 越界了
        // fast != null && fast.next != null √
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 后一半链表头节点
        ListNode newHead = slow.next;
        // 断链
        slow.next = null;

        ListNode left = head;
        ListNode right = reverse(newHead);

        // right == null，结束 while 循环
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
