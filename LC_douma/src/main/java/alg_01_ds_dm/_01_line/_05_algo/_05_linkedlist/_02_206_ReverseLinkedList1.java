package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 21:36
 * @Version 1.0
 */
public class _02_206_ReverseLinkedList1 {

    /*
        206. 反转链表
        给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

        示例 1
        输入：head = [1,2,3,4,5]
        输出：[5,4,3,2,1]

        提示：
        链表中节点的数目范围是 [0, 5000]
        -5000 <= Node.val <= 5000
     */

    // KeyPoint 方法一 迭代法 => 需要掌握，有可能是复杂链表题目的预处理操作
    public ListNode reverseList1(ListNode head) {
        // 前
        ListNode pre = null;
        // 中
        ListNode cur = head;
        // 后
        ListNode next;
        while (cur != null) {
            // 记录 cur 后一个节点，避免移动 cur.next 指针，导致找不到后面一个节点
            // 即：移动指针之前，先将后面一个节点信息，保存在 next 中，方便后续使用
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // cur 为 null，pre 在链表尾节点位置
        return pre;
    }


}
