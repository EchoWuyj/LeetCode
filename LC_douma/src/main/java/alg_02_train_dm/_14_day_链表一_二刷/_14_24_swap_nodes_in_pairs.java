package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 14:52
 * @Version 1.0
 */
public class _14_24_swap_nodes_in_pairs {

      /*
          24. 两两交换链表中的节点
          给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
          你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

          输入：head = [1,2,3,4]
          输出：[2,1,4,3]

          提示：
          链表中节点的数目在范围 [0, 100] 内
          0 <= Node.val <= 100

     */

    public ListNode swapPairs(ListNode head) {

        // KeyPoint 1.链表节点足够

        // 交换前 => 原始链表
        // 交换 first 和 second 节点需要使用 prev 和 next，故一共定义 4 个指针

        // dummy
        //  ↓
        // -1      1      2       3     4     5  ...
        //  ↑      ↑      ↑       ↑
        // prev  first  second  next

        // 头插法 => 交换节点操作
        // first.next = next;
        // second.next = prev.next;
        // prev.next = second;

        // 交换后
        // -1      2       1      3     4     5  ...
        //  ↑      ↑       ↑      ↑
        // prev  second  first  next

        // 更新指针操作
        // 注意：这里更新指针操作，前提是链表节点数足够
        // -1      2       1      3      4      5  ...
        //                 ↑      ↑      ↑      ↑
        //               prev   first  second  next

        // prev = first;
        // first = next;
        // second = first.next

        // 因为 first 和 second 是一定存在的，故可以直接

        // KeyPoint 2.链表节点不够，first == null 情况

        // 交换前 => 原始链表
        // dummy
        //  ↓
        // -1      1      2     null
        //  ↑      ↑      ↑       ↑
        // prev  first  second  next

        // 交换后
        // -1      2       1    null    null
        //  ↑      ↑       ↑      ↑
        // prev  second  first  next

        // 更新后
        // -1      2       1    null    null
        //                 ↑     ↑      ↑
        //               prev  first  second
        // 原始链表 next 为 null
        // => first == null
        // => 提前结束 while 循环

        // 链表没有节点或者只有一个节点，不用交换
        if (head == null || head.next == null) return head;

        // KeyPoint 只要 head 节点可能发生变化 => 使用 dummy 节点
        // 在交换过程中，head 节点发生改变，则需要使用 dummy 节点，统一操作
        ListNode dummy = new ListNode(-1);
        // KeyPoint dummy 指针串联 head 节点
        // 一定不能将其漏掉，多次因为这个原因，导致出现的 bug
        dummy.next = head;
        // 统一定义 4 个指针
        ListNode prev = dummy;
        ListNode first = head;
        ListNode second = head.next;
        ListNode next = second.next;

        // 因为 second 靠后的节点，用于判断 while 循环结束
        // 若 second == null，则 first 和 second 构不成两个节点，则不用执行 while 中的交换操作
        while (second != null) {

            // KeyPoint 头插法 => 代码模块化 => 推荐使用
            // 交换节点操作
            first.next = next;
            second.next = prev.next;
            prev.next = second;

            // 统一更新 4 个指针
            prev = first;
            first = next;
            // 更新时需要判空，提前结束 while 循环
            if (first == null) break;
            // 上面对 first 进行判空，first.next 调用不会出错
            // 若 first.next == null，second 更新，即 second == null，结束 while 循环
            second = first.next;
            // 判空
            if (second != null) next = second.next;
        }
        return dummy.next;
    }
}
