package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 18:59
 * @Version 1.0
 */
public class _04_82_remove_duplicates_from_sorted_list_ii {

     /*
        82. 删除排序链表中的重复元素 II
        存在一个 按升序排列 的链表，给你这个链表的头节点 head ，
        请你删除链表中所有存在数字重复情况的节点，
        只保留原始链表中 没有重复出现 的数字。

        返回同样按升序排列的结果链表

        输入：head = 1->2->3->3->4->4->5
        输出：1->2->5

        输入：head = [1,1,1,2,3]
        输出：[2,3]

        提示：
        链表中节点数目在范围 [0, 300] 内
        -100 <= Node.val <= 100
        题目数据保证链表已经按升序排列

     */

    // KeyPoint 写代码经验
    // 编写代码，要同时满足题目已给的多个测试用例，而不是只是满足其中一个
    // 只有满足多个测试用例，代码才可能是有更强的健壮性

    // KeyPoint 注意事项
    // 重复元素可能连续存在多个，得使用 while 循环，使用有限个指针无法解决
    // 比如：firPre，secPre，cur，firNext，secNext
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        // KeyPoint 链表头节点
        // 涉及头节点的链表操作，一般都是需要 dummy
        // 凡是链表操作，提醒自己是否可能为 head 节点，是则需要使用 dummy

        // 待删除节点可能是头节点，故需要使用 dummy
        ListNode dummy = new ListNode(-101);
        dummy.next = head;
        // 删除元素，需要的 pre
        ListNode pre = dummy;
        // 找到相同元素指针
        ListNode cur = head;
        while (cur != null) {
            // 存在重复元素
            if (cur.next != null && cur.val == cur.next.val) {
                // KeyPoint 学习 do while 循环写法
                // 先移动 cur 指针，再去执行 while 判断条件
                // 跳出 while 循环，cur 为重复元素的最后一个元素
                do {
                    cur = cur.next;
                } while (cur.next != null && cur.val == cur.next.val);

                pre.next = cur.next;
                // KeyPoint 最好养成断链的习惯，避免形成链表环路
                cur.next = null;
                cur = pre.next;
            } else { // 没有重复元素，更新 pre 位置
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
