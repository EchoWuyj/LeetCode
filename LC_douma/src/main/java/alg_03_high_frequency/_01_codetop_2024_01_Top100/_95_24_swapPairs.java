package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _95_24_swapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        // 依次 next 即可
        ListNode first = head;
        ListNode second = first.next;
        ListNode next = second.next;

        while (second != null) {
            // 头插法
            // 规律： 统一调整 next 指针
            first.next = next;
            second.next = pre.next;
            pre.next = second;

            // 统一更新 4 个指针
            pre = first;
            first = next;
            // 如果 first 指针为 null，则直接 break
            if (first == null) break;
            second = first.next;
            if (second != null) {
                next = second.next;
            }
            // 如果 second 为 null，凑不成一对节点，while 循环不满足，直接退出循环
        }
        return dummy.next;
    }
}
