package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 18:16
 * @Version 1.0
 */
public class _02_206_reverseList_01 {

    // 反转链表
    // 迭代解法
    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        // cur 需要从 head 开始，不能从 null 开始
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            // 从后往前移动指针，指向前面一个节点
            cur.next = pre;
            // 先移动 pre，再去移动 cur
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
