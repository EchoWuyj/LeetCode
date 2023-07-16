package alg_02_train_dm._15_day_链表二_二刷;

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

     */

    // 注意：不能将链表放到数组中，判断数组是否能是回文
    // 时间复杂度 O(n) 两次遍历，一次找中点和反转，一次比较
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // KeyPoint 中点 mid 靠左
        // slow 从 head 开始，fast 从 head.next，slow 中点是靠右

        // 数组
        // index 0 1 2 3 4 5
        // value 1 2 3 3 2 1
        //           ↑
        //          mid

        // 找中点(靠左)
        ListNode slow = head;
        ListNode fast = head.next;

        // KeyPoint 严格遵守判空条件，逻辑的递进性
        // 1.fast.next != null && fast != null × => 若 fast == null，则 fast.next 越界了
        // 2.fast != null && fast.next != null √
        // KeyPoint 注意：fast 非空判断是 && 连接，同时成立，而不是 ||，只成立一个
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 1  2  3    3  2  1
        //       ↑    ↑
        //      mid newHead
        //       ↑
        //      slow

        // 后一半链表头节点
        ListNode newHead = slow.next;
        // 断链
        slow.next = null;

        // 重新统一起点
        ListNode left = head;
        // 反转，right 起点
        ListNode right = reverse(newHead);

        // 链表若是奇数个，mid 在 left 开头侧链表中
        // 若能将 right 开头侧链表遍历完，没有不同值即可
        // => right == null，结束 while 循环，返回 true
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            // 先将 next 保存
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
