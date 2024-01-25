package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 18:16
 * @Version 1.0
 */
public class _02_206_reverseList_02 {
    public ListNode reverseList(ListNode head) {
        // 递归实现
        if (head == null || head.next == null) return head;

        // 链表最后一个节点
        ListNode tail = reverseList(head.next);

        // 指针注意方向， 不要搞反了
        // 前指针，后节点
        head.next.next = head;
        head.next = null;

        return tail;
    }
}
