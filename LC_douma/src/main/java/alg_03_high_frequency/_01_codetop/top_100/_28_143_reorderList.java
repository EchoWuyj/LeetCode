package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 15:55
 * @Version 1.0
 */
public class _28_143_reorderList {

    // 重排链表
    // 直接模拟
    public void reorderList(ListNode head) {
        // 特判
        if (head == null) return;

        // 1.获取中点
        // 原链表长度奇数
        //  _  _   _     _  _
        //  ↑      ↑     ↑
        // list1  slow list2
        ListNode mid = getMid(head);
        // 分割后的两个链表，list1 长链表，list2 短链表
        ListNode list1 = head;
        ListNode list2 = mid.next;
        mid.next = null;

        // 2.反转链表
        list2 = reverseList(list2);
        // 3.合并链表
        mergeList(list1, list2);
    }

    // 1.获取链表中点
    public ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // fast 先走一步，slow 靠左 => fast 多走一步，slow 少走一步，故 slow 靠左
        // fast 和 slow 相同起点，slow 考右
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 2.反转链表
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

    // 3.合并链表
    public void mergeList(ListNode l1, ListNode l2) {
        // 定义串联指针
        ListNode next1;
        ListNode next2;

        // while 循环判断，必须保证两者都同时不为 null
        while (l1 != null && l2 != null) {
            // 先记录好下个位置
            next1 = l1.next;
            next2 = l2.next;

            // l1 串联 l2
            l1.next = l2;
            // 移动 l1 到下个位置
            l1 = next1;

            // l2 串联 l1
            l2.next = l1;
            // 移动 l2 到下个位置
            l2 = next2;
        }
    }
}
