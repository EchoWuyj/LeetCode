package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 14:53
 * @Version 1.0
 */
public class _15_25_reverse_nodes_in_k_gro_up {
    
      /*
        25. K 个一组翻转链表
        给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
        k 是一个正整数，它的值 小于 或 等于 链表的长度。
        如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

        输入：head = [1,2,3,4,5], k = 2
        输出：[2,1,4,3,5]

        输入：head = [1,2,3,4,5], k = 3
        输出：[3,2,1,4,5]

        提示：
        链表中的节点数目为 n
        1 <= k <= n <= 5000
        0 <= Node.val <= 1000

        进阶：
        你可以设计一个只使用常数额外空间的算法来解决此问题吗？
        你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */

    public ListNode reverseKGroup(ListNode head, int k) {

        // KeyPoint 链表调整操作的关键
        // 找到核心位置指针，通过调整指针实现题目要求

        // 原始链表，定义指针位置
        // dummy，k = 3
        //  ↓
        // -1 → 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → null
        //  ↑   ↑       ↑   ↑
        // pre first  last next

        // KeyPoint 1.链表调整操作
        // 断链 last.next = null
        // 反转 reverse(first)
        // 串联 pre.next = last;
        //     first.next = next;

        // dummy，k = 3
        //  ↓
        // -1 → 3 → 2 → 1 → 4 → 5 → 6 → 7 → 8 → null
        //  ↑   ↑       ↑   ↑
        // pre last  first next
        // 注意：反转后 first 已经变成 last，即虽然变量名是 first
        //       但是实际上语义是 last， pre 移动该位置

        // KeyPoint 2.移动指针
        // pre = first;
        // first = next;
        // last = first; 之后经过 for 循环，last 到最终的位置
        // next = last.next

        // dummy，k = 3
        //  ↓
        // -1 → 3 → 2 → 1 → 4 → 5 → 6 → 7 → 8 → null
        //  ↑   ↑       ↑   ↑
        // pre last  first next
        //              ↑   ↑            ↑
        //             pre first        next
        //                  ↑        ↑
        //                 last     last

        if (head == null || head.next == null || k == 1) return head;
        // head 节点会发生改变，则需要使用 dummy 节点，统一操作
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode first = head;
        ListNode last = first;
        ListNode next = first;

        // 这里 last 因为没有确定位置，所以使用 first 作为循环条件
        while (next != null) {
            for (int i = 0; i < k - 1; i++) {
                last = last.next;
                // 若 last 为空，结束循环
                if (last == null) return dummy.next;
            }
            // 记录 next
            if (last != null) next = last.next;
            else return dummy.next;

            // 断链 => k 个一组
            // pre 和 last 断链
            pre.next = null;
            // 是 last，而不是 next
            last.next = null;

            // 反转链表
            reverse(first);

            // KeyPoint 反转后头尾变化
            // 头变尾，尾变头，移动指针，串联反转后链表
            // 此时 last → first，first → last，使用时不要混淆了
            pre.next = last;
            first.next = next;

            // 移动指针
            // 反转后 first 已经变成 last，即虽然变量名是 first
            // 但是实际上语义是 last， pre 移动该位置
            pre = first;
            if (next != null) first = next;
            last = first;
        }
        return dummy.next;
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
