package alg_01_新手班_zcy.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code06_MergeTwoSortedLinkedList {

    // 测试链接：https://leetcode.cn/problems/merge-two-sorted-lists

    // 给定两个有序链表的头节点head1和head2
    // 返回合并之后的大链表,要求依然有序例子

    // 例如  1->3->3->5->7 2->2->3->3->7
    // 返回  1->2->2->3->3->3->3->5->7

    // KeyPoint 先使用简单的例子,将流程梳理清楚,再去想抽象化的事情

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {

        // 边界条件
        if (head1 == null || head2 == null) {
            // 进入if代码里面就表示head1和head2至少有1个为null
            // 即使head1和head2都为null的情况,也是包含在内的,返回的结果也会为null
            return (head1 == null) ? head2 : head1;
        }

        // 比较原来两条链表的头节点,head1和head2谁小,谁做新链表的头节点
        // 没有创建新的链表,只是将老的链表串在一起了
        ListNode head = (head1.val <= head2.val) ? head1 : head2;

        // cur1指向小值头节点(原来所在的链表的下一个节点)
        ListNode cur1 = head.next;
        // cur2指向大值头节点(另外一条链表的头节点)
        ListNode cur2 = (head == head1) ? head2 : head1;

        // 不断遍历的指针
        // head指针是保持在原来位置不发生变化的
        ListNode pre = head;

        // head1和head2同时不为null
        while (cur1 != null && cur2 != null) {
            // 取等的情况也是可以的
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }

        // 从while中跳出,谁不为null,就接在原来链表的后面
        pre.next = (cur1 != null) ? cur1 : cur2;
        return head;

        // KeyPoint 指针和节点区别

        // ListNode node = new ListNode();
        // pre = node
        // head = pre
        // 指针(head) = 节点(pre指向的节点)

        // pre = pre.next;
        // head不会发生变化的,head还是指向原来的内存区域

    }
}
