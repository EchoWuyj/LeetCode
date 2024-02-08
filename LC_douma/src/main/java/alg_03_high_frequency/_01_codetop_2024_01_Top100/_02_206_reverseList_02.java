package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 18:16
 * @Version 1.0
 */
public class _02_206_reverseList_02 {

    // 反转链表
    // 递归实现
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) return head;

        // 链表最后一个节点
        // 1 -> 2 -> 3
        // 经过 reverseList 之后
        // 1 <- 2 <- 3
        //         tail
        ListNode tail = reverseList(head.next);

        // 指针注意方向，不要搞反了
        // 前指针 = 后节点
        // head.next 为 head 后面一个节点
        // .next 为 head 后面一个节点的指针
        head.next.next = head;
        head.next = null;

        return tail;
    }
}
