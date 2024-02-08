package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 9:41
 * @Version 1.0
 */
public class _44_148_sortList {

    // 排序链表
    // 本质：在链表中使用归并排序
    // 分治思想
    public ListNode sortList(ListNode head) {

        // 递归边界
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        // 归并排序，需要 slow 中点靠左，fast得先走一步
        ListNode fast = head.next;

        // fast 条件同时满足
        // 其中一个不满足，结束 while 循环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode leftHead = head;
        ListNode rightHead = slow.next;
        slow.next = null;

        // 归并排序
        // 对两部分链表 leftHead 和 rightHead，分别递归调用主函数 sortList
        ListNode left = sortList(leftHead);
        ListNode right = sortList(rightHead);

        // 对已经有序的链表 left 和 right 进行合并
        return merge(left, right);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);
        // 串联指针
        ListNode cur = dummy;

        // 注意：while 循环条件里面都是 != 条件，不要写成 ==
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }
}
