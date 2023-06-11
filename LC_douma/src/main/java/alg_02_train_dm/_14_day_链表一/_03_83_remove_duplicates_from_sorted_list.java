package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 10:33
 * @Version 1.0
 */
public class _03_83_remove_duplicates_from_sorted_list {

      /*
        83. 删除排序链表中的重复元素
        存在一个按升序排列的链表，给你这个链表的头节点 head ，
        请你删除所有重复的元素，使每个元素 只出现一次 。

        返回同样按升序排列的结果链表。

        输入：head = 1 -> 1 -> 2 -> 3 -> 3 -> null
        输出：1 -> 2 -> 3 -> null

        提示：
        链表中节点数目在范围 [0, 300] 内
        -100 <= Node.val <= 100
        题目数据保证链表已经按升序排列
     */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 链表升序排列，相同值节点紧靠在一起
        // prev 和 curr 用于比较值，从而判断节点是否重复，重复删除 curr
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val == prev.val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            // curr 始终是 prev 的前一个节点 => 更新 cur
            curr = prev.next;
        }
        return head;
    }
}
