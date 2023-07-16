package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 20:39
 * @Version 1.0
 */
public class _10_92_reverse_linked_list_ii1 {
     /*
        92. 反转链表 II
        给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
        请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

        输入：head = 1->2->3->4->5, left = 2, right = 4
        输出： 1->4->3->2->5

             1->2->3->4->5
        索引 1  2  3  4  5

        提示：
            链表中节点数目为 n
            1 <= n <= 500
            -500 <= Node.val <= 500
            1 <= left <= right <= n

        进阶： 你可以使用一趟扫描完成反转吗？
     */

    // KeyPoint 方法一 直接模拟
    // 反转部分的链表遍历了 2 次 => 不满足题目遍历 1 次的要求
    public ListNode reverseBetween1(ListNode head, int left, int right) {

        // 因为有头节点也可能变化，所以为了统一逻辑，这里设置虚拟节点
        // KeyPoint 反正链表题目，涉及修改操作，都是使用 dummy，最保险的方式
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 1. 从虚拟节点走 left - 1 步，来到 left 节点的前一个节点
        // 如：left = 3，走 2 步，到 2 节点
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode leftNode = pre.next;

        // 2. 从 leftNode 节点开始走 right - left 步，来到 right 节点
        // KeyPoint 保证 leftNode 指针不变化，后续还会再使用到 leftNode
        // 使用 rightNode 接替 leftNode，从而保证 leftNode 不再变化
        ListNode rightNode = leftNode;
        for (int i = 0; i < right - left; i++) {
            // KeyPoint
            // for 循环里面移动 rightNode，而不是 leftNode
            // leftNode 位置一旦确定，其位置就不能改变了
            rightNode = rightNode.next;
        }
        // 需要记住 pre 和 post，为了将 leftNode 和 rightNode 反转后的链表串联
        ListNode post = rightNode.next;

        // 3.切断得到 left 到 right 的子链表
        // KeyPoint 断链操作容易遗忘，需要加强意识
        pre.next = null;
        rightNode.next = null;

        // KeyPoint 使用某个指针 leftNode 注意事项
        // 在使用 leftNode 之前，确定 leftNode 是否发生变化了，可能在定义 leftNode 后
        // leftNode 又移动了，导致 leftNode 不是原来的 leftNode

        // 4.反转 leftNode 到 rightNode => 单独抽取成一个方法
        reverseList(leftNode);

        // 5.将反转后的子链表接回到原链表
        pre.next = rightNode;
        leftNode.next = post;

        return dummy.next;
    }

    // 反转链表-迭代
    private void reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }
}
