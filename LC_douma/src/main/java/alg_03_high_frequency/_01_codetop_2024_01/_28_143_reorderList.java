package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 15:55
 * @Version 1.0
 */
public class _28_143_reorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode mid = getMid(head);

        // 两个链表
        ListNode list1 = head;
        ListNode list2 = mid.next;
        mid.next = null;
        list2 = reverseList(list2);

        mergeList(list1, list2);
    }

    // 获取链表中点
    public ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // fast 先走一步，slow 靠左
        // fast 和 slow 相同起点，slow 考右
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 合并链表
    public void mergeList(ListNode l1, ListNode l2) {
        // 串联指针
        ListNode cur1;
        ListNode cur2;

        // while 循环判断，必须保证两者都时不为 null 才行
        while (l1 != null && l2 != null) {
            // 定位下个位置，标记好下个位置
            cur1 = l1.next;
            cur2 = l2.next;

            l1.next = l2;
            // 移动l1
            l1 = cur1;

            l2.next = l1;
            l2 = cur2;
        }
    }
}
