package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 18:58
 * @Version 1.0
 */
public class _29_142_detectCycle_02 {
    public ListNode detectCycle(ListNode head) {
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        // && 同时成立
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 如果 fast 和 slow 相遇，则说明链表存在环
            if (fast == slow) {
                ListNode cur = head;
                while (slow != cur) {
                    slow = slow.next;
                    cur = cur.next;
                }
                // 在 while 循环结束进行 return 返回，而不是在外层 while 循环外面 return
                return cur;
            }
        }
        return null;
    }
}
