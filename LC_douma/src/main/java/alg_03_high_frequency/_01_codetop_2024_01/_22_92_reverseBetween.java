package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:11
 * @Version 1.0
 */
public class _22_92_reverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 头节点可能发生变化
        ListNode dummy = new ListNode(-1);
        // 串联 head 节点
        dummy.next = head;
        ListNode pre = dummy;

        // left 前一个位置
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 标记 cur 节点位置
        ListNode cur = pre.next;

        // 头插法 => 逆时针调整
        for (int i = 0; i < right - left; i++) {
            // cur 节点位置没有发生变化
            // 先确定 next 节点位置
            ListNode next = cur.next;
            // 移动 cur 节点的 next 指针
            cur.next = next.next;
            // 移动 next 节点的 next 指针
            next.next = pre.next;
            // 移动 pre 节点的 next 指针
            pre.next = next;
        }
        return dummy.next;
    }
}
