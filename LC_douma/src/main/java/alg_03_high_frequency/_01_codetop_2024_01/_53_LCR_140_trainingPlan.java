package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 18:49
 * @Version 1.0
 */
public class _53_LCR_140_trainingPlan {
    public ListNode trainingPlan(ListNode head, int cnt) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // fast 和 slow 都是从 dummy 位置开始，而不是从 head 位置开始
        // 凡是涉及修改操作，基本都是从 dummy 位置开始，一般通过 fast 和 slow 找中点，都是从 head 节点开始
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < cnt + 1; i++) {
            fast = fast.next;

            // 不能使用 cur，而是使用 fast，否则多走 cnt + 1 步，没有体现
            // cur = cur.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 直接返回倒数第 cnt 个训练项目编号(包括 cnt 编号后续的节点)，即以 cnt 开头的链表
        return slow.next;
    }
}
