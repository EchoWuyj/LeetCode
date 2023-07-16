package alg_02_train_dm._14_day_链表一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 14:51
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts1 {
     /*
        725. 分隔链表
        给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
        每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

        这 k 个部分应该按照在链表中出现的顺序进行输出，
        并且排在前面的部分的长度应该 大于 或 等于 后面的长度。

        返回一个符合上述规则的链表的列表。

        输入：root = 1->2->3->4->5->6->7->8->9->10      k = 3
        输出：[
                1->2->3->4,
                5->6->7,
                8->9->10
              ]

        输入：root = 1->2->3         k = 5
        输出：[
                1,
                2,
                3,
                null,
                null
              ]

        提示：
        root 的长度范围： [0, 1000].
        输入的每个节点的大小范围：[0, 999].
        k 的取值范围： [1, 50].
     */

    // KeyPoint 方法一 拷贝链表节点
    // 基于原始链表拷贝节点进行分隔 => 浪费空间
    // 时间复杂度 O(n+k)
    // 遍历两次链表：链表长度和分隔链表 => 两个 for 循环，且数据规模不同，则 O(n+k)
    // 空间复杂度 O(n)
    public ListNode[] splitListToParts1(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;

        while (cur != null) {  // O(n)
            len++;
            cur = cur.next;
        }
        // 分隔每部分至少的节点数
        int width = len / k;
        // 余数，分隔前几个部分长度加 1
        int remainder = len % k;
        // 记住 remainder 变量命名方式

        // cur 指针重新回到 head 位置
        cur = head;

        // 定义 k 组链表
        // 若 k > len，空的位置，不赋值，则默认是 null
        ListNode[] res = new ListNode[k];
        for (int i = 0; i < k; i++) { // O(k)
            // KeyPoint 标记头节点
            // 为了方便找到分隔每部分的头节点
            ListNode dummy = new ListNode(-1);
            // 实际遍历的指针 node
            ListNode node = dummy;
            // i < remainder => 对应 i 组是有余数的，故长度需要多加 1
            // KeyPoint 注意事项
            // 1.remainder 是和循环变量 i 比较，不是和 k 比较，k 是固定的
            // 2.i < remainder ? 1 : 0，只是 1 和 0 的选择，width 在最前面，独立出来的
            int realWidth = width + (remainder > i ? 1 : 0);
            for (int j = 0; j < realWidth; j++) {  //
                node.next = new ListNode(cur.val);
                node = node.next;
                // KeyPoint 防御式编程
                // 没有 if 判空，OJ 也是能通过的，说明 cur 不可能为空
                // 但是为了 cur 更加严谨，加上 if 判空，避免考虑不周的情况
                if (cur != null) cur = cur.next;
            }
            res[i] = dummy.next;
        }
        return res;
    }
}
