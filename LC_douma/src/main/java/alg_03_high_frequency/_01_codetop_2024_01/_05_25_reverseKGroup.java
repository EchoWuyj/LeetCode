package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 19:16
 * @Version 1.0
 */
public class _05_25_reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null || k == 1) return head;

        ListNode dummy = new ListNode(-1);
        // 串联
        dummy.next = head;

        ListNode pre = dummy;
        ListNode first = head;
        // 同一指向 first
        ListNode last = first;
        ListNode next = first;

        while (next != null) {

            // 确定 last 位置
            // last 从 first 位置开始，故为 k-1
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                if (last == null) return dummy.next;
            }

            // 确定 next 位置
            // 此时 last 一定不为 null，last 为 null
            // 则 if 判断条件不满足，在 for 循环中就已经 return 了
            if (last != null) {
                next = last.next;
            }

            // 首尾两头断链
            pre.next = null;
            last.next = null;

            // 反转
            reverse(first);

            // 连接
            pre.next = last;
            first.next = next;

            // 更新指针位置
            pre = first;
            // first 移动到 next 位置
            if (next != null) first = next;
            // last 和 first 指向同一个位置
            last = first;
            next = first;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            // cur next 指针指向 pre 节点
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
