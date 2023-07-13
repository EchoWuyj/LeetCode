package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 10:11
 * @Version 1.0
 */
public class _02_237_delete_node_in_a_linked_list {
    /*
        237. 删除链表中的节点
        请编写一个函数，使其可以删除某个链表中给定的(非末尾)节点。
        传入函数的唯一参数为：要被删除的节点 。

        输入：4 -> 5 -> 1 -> 9 -> null，node = 5
        输出：4 -> 1 -> 9 -> null

        提示：
        链表至少包含两个节点。
        链表中所有节点的值都是唯一的。
        给定的节点为非末尾节点并且一定是链表中的一个有效节点。
        不要从你的函数中返回任何结果。
     */

    // 注意：本题形参中只有待删除的节点，没有链表的表头
    //      即无法通过 prev 删除节点，区别于 203 题
    // 思路：
    // 1.并不直接将 node 节点从链表中断开，而是用后面节点值覆盖 node
    // 2.链表后续的节点，依次类推，最后删除链表的尾节点
    // 时间复杂度：O(n)
    // 移动了 node 往后所有节点，效率不高
    public void deleteNode1(ListNode node) {
        // pre 为 node 的前一个节点
        ListNode pre = null;
        // node 不为 null，则一直遍历结尾，将尾节点删除
        while (node != null) {
            ListNode next = node.next;
            if (next != null) {
                // next != null 说明不是最后一个节点
                // 本题通过覆盖原值的方式实现删除，没有调整指针
                node.val = next.val;
            } else {
                // next == null，说明 node 为最后一个节点
                // pre.next = null，即将 node 删除 (链表至少包含两个节点)
                // 执行 pre.next 之前，最好保证 pre 不为 null
                if (pre != null) pre.next = null;
            }
            // 两指针后移
            pre = node;
            node = node.next;
        }
    }

    // 优化：
    // 实际上只需要将 next 的值覆盖掉 node 值，并将 next 从链表中断开即可
    // 时间复杂度：O(1)
    // 只是移动了 node 一个节点，效率高
    public void deleteNode(ListNode node) {
        // node 节点的下个节点 next，将其赋值给 node.val
        node.val = node.next.val;
        // 移除 node 的下个节点 next
        ListNode next = node.next;
        node.next = next.next;
        next.next = null;

        // 操作逻辑

        // 1.原始状态
        // node → next → next.next

        // 2.next 赋值给 node
        // next' → next  → next.next

        // 3.删除 next
        // next' →  next.next => 等价于：删除 node

    }
}
