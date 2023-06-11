package alg_02_train_dm._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:38
 * @Version 1.0
 */
public class _03_86_partition_list {
    /*
       86. 分隔链表
       给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
       使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

       你应当 保留 两个分区中每个节点的初始相对位置。

       输入：head = 1->4->3->2->5->2->NULL, x = 3
       输出：1->2->2->4->3->5->NULL
    */

    // 开辟新的链表 => 空间复杂度 O(n)
    // 1. < x => 创建一个链表
    // 2. >= x => 创建一个链表
    // 3. 将两个链表串联

    // 不创建链表节点，通过修改链表节点指针实现 => 原地解法
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        // 定义 small 和 large 进行串联指针，不用创建新的链表节点
        ListNode smallHead = new ListNode(-1);
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(-1);
        ListNode large = largeHead;

        // head 遍历原链表指针
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        // head 遍历到链表结尾节点，large 和 small 指针需要最后调整指针
        // 即断链和串联，保证两部分链表能正确相链接
        large.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }
}
