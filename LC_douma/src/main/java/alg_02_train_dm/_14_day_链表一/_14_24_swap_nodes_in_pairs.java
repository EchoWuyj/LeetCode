package alg_02_train_dm._14_day_链表一;

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

          输入：head = 1->2->3->4->5->NULL
          输出：2->1->4->3->5->NULL
     */

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        // head 节点发生改变，则需要使用 dummyNode 节点，统一操作
        ListNode dummyNode = new ListNode(-1);
        // KeyPoint 一定不能将其漏掉，多次因为这个原因，导致出现的 bug
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode first = head;
        ListNode second = head.next;
        // second 靠后的节点，用于判断 while 循环结束
        // second == null，后面不存在两个节点，即不用执行 while 中的交换操作
        while (second != null) {
            ListNode next = second.next;
            // 原始链表
            // -1      1      2       3     4     5  ...
            // prev  first  second  next

            // 交换操作
//            prev.next = second;
//            second.next = first;
//            first.next = next;

            // KeyPoint 头插法版本 => 代码模块化 => 推荐使用
            first.next = next;
            second.next = prev.next;
            prev.next = second;

            // 交换后
            // -1      2       1      3     4     5  ...
            // prev  second  first  next

            // 更新后
            // -1  2   1      3      4      5  ...
            //       prev   first  second  next

            // 更新指针操作
            prev = first;
            first = next;

            // first == null 情况

            // 交换后
            // -1      2       1    null
            // prev  second  first  next

            // 更新后
            // -1      2       1    null    null
            // prev  second  first  next
            //                prev  first  second
            // 提前结束 while 循环
            if (first == null) break;
            // second 更新之后，若 second == null，也不执行 while 循环
            // KeyPoint 上面对 first 进行判空，first.next 调用不会出错
            second = first.next;
        }
        return dummyNode.next;
    }
}
