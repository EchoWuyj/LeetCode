package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 14:53
 * @Version 1.0
 */
public class _15_25_reverse_nodes_in_k_gro_up {
    
      /*
        25. K 个一组翻转链表
        给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
        k 是一个正整数，它的值小于或等于链表的长度。
        如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

        输入：1->2->3->4->5    k=2
        输出：2->1->4->3->5

        输入：1->2->3->4->5->6->7->8    k=3
        输出：3->2->1->6->5->4->7->8

        进阶：
        你可以设计一个只使用常数额外空间的算法来解决此问题吗？
        你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        // head 节点发生改变，则需要使用 dummyNode 节点，统一操作
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        ListNode first = head;
        ListNode last = first;
        // 这里 last 因为没有确定位置，所以使用 first 作为循环条件
        while (first != null) {
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                // 若 last 为空，结束循环
                if (last == null) return dummyNode.next;
            }
            // 记录 next
            ListNode next = last.next;
            // 断链 => k 个一组，最后一个节点断链
            // 是 last，而不是 next
            last.next = null;

            // 反转链表
            reverse(first);

            // 头变尾，尾变头，移动指针，串联反转后链表
            prev.next = last;
            first.next = next;

            // 移动指针
            // 反转后 first 已经变成 last，即虽然变量名是 first，但是实际上语义是 last， prev 移动该位置
            prev = first;
            first = next;
            last = first;
        }
        return dummyNode.next;
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
