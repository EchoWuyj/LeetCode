package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 18:59
 * @Version 1.0
 */
public class _04_82_remove_duplicates_from_sorted_list_ii {

     /*
        82. 删除排序链表中的重复元素 II
        存在一个按升序排列的链表，给你这个链表的头节点 head ，
        请你删除链表中所有存在数字重复情况的节点，
        只保留原始链表中 没有重复出现 的数字。

        返回同样按升序排列的结果链表

        输入：head = 1->2->3->3->4->4->5
        输出：1->2->5

        提示：
        链表中节点数目在范围 [0, 300] 内
        -100 <= Node.val <= 100
        题目数据保证链表已经按升序排列

     */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 待删除节点可能是头节点，故需要使用 dummyNode
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        // 删除元素，需要的 prev
        ListNode prev = dummyNode;
        // 找到相同元素指针
        ListNode curr = head;
        while (curr != null) {
            // 存在重复元素
            if (curr.next != null && curr.val == curr.next.val) {
                do {
                    curr = curr.next;
                } while (curr.next != null && curr.val == curr.next.val);
                // 跳出 while 循环，cur 为重复元素的最后一个元素
                prev.next = curr.next;
                curr.next = null;
            } else { // 没有重复元素，更新 prev 位置
                prev = curr;
            }
            curr = prev.next;
        }
        return dummyNode.next;
    }
}
