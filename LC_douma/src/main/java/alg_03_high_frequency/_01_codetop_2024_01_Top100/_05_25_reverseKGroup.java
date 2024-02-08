package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 19:16
 * @Version 1.0
 */
public class _05_25_reverseKGroup {

    // K 个一组翻转链表
    // 直接模拟
    public ListNode reverseKGroup(ListNode head, int k) {

        // 判空
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummy = new ListNode(-1);
        // 串联
        dummy.next = head;

        // 定义 4 个指针
        ListNode pre = dummy;
        ListNode first = head;
        // last 一组结尾；next 下一组开头
        // last 和 next 同一指向 first
        ListNode last = first;
        ListNode next = first;

        // 下一组第一元素不为空，循环执行
        while (next != null) {

            // 确定 last 位置
            // last 从 first 位置开始，故为 k-1
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                // 不满足一组情况，直接 return
                if (last == null) return dummy.next;
            }

            // 确定 next 位置
            // if 判断可以省略，因为 last 一定不为 null，此处加上更加严谨
            // 若 last 为 null，则 if 判断条件不满足，在 for 循环中就已经 return 了
            if (last != null) {
                next = last.next;
            }

            // 首尾两头 断链
            pre.next = null;
            last.next = null;

            // 反转
            reverse(first);

            // 首尾两头 连接
            pre.next = last;
            first.next = next;

            // 更新 4 个指针位置
            // 反转后 first 为前一组最后一个位置，pre 移动到该位置
            pre = first;
            // 下一组第一元素不为空，first 移动到 next 位置
            if (next != null) first = next;
            // last 和 next 同样指向 first
            last = first;
            next = first;
        }
        return dummy.next;
    }

    // 反转链表
    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            // cur next 指针指向 pre 节点
            cur.next = pre;
            // 先移动 pre，再去移动 cur
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
