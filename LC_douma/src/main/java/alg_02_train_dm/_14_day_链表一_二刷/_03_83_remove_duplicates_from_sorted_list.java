package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 10:33
 * @Version 1.0
 */
public class _03_83_remove_duplicates_from_sorted_list {

      /*
        83. 删除排序链表中的重复元素
        存在一个 按升序排列 的链表，给你这个链表的头节点 head ，
        请你删除所有重复的元素，使每个元素 只出现一次 。
        返回同样按升序排列的结果链表。

        输入：head = 1 -> 1 -> 2 -> 3 -> 3 -> null
        输出：1 -> 2 -> 3 -> null

        提示：
        链表中节点数目在范围 [0, 300] 内
        -100 <= Node.val <= 100
        题目数据保证链表已经按升序排列
     */

    // KeyPoint 链表题目，通过画示意图，明确串联指针关系
    public ListNode deleteDuplicates(ListNode head) {
        // 先特判，保证后续引用不为 null
        // 否则 pre.next 或者 cur.next 可能为 null
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        // 链表升序排列，相同值节点紧靠在一起
        // => 若没有这个条件，相同值节点可能分散，无法通过移动指针，将其删除
        // pre 和 cur 用于比较值，从而判断节点是否重复，重复删除 cur
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            // 两者值相同，删除节点
            if (pre.val == cur.val) {
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            } else {
                // 两者值不同，向前移动指针
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
