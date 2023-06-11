package alg_02_train_dm._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 14:51
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts {
     /*
        725. 分隔链表
        给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
        每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

        这 k 个部分应该按照在链表中出现的顺序进行输出，
        并且排在前面的部分的长度应该大于或等于后面的长度。

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

    // KeyPoint 方法一 基于原始链表拷贝节点进行分隔 => 浪费空间
    // 时间复杂度 O(n)
    // 遍历两次链表： 链表长度和分隔链表
    // KeyPoint 分析时间复杂度，不是简单直接，两个 for 循环，且数据规模不同，则 O(n+k)
    //          而是需要分析具体执行操作，从而计算时间复杂度
    // 空间复杂度 O()
    public ListNode[] splitListToParts1(ListNode root, int k) {
        int len = 0;
        ListNode curr = root;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        // 分隔每部分至少的节点数
        int width = len / k;
        // 余数，分隔前几个部分长度加 1
        int remainder = len % k;
        // 记住 remainder 变量命名方式

        // k > len，空的位置，不赋值，则默认是 null
        ListNode[] res = new ListNode[k];
        curr = root;
        for (int i = 0; i < k; i++) {
            // 为了方便找到分隔每部分的头节点
            ListNode dummyNode = new ListNode(-1);
            ListNode node = dummyNode;
            // 注意：i < remainder，是和循环变量 i 比较，不是和 k 比较
            // KeyPoint i < remainder ? 1 : 0，只是 1 和 0 的选择，不用加上 width
            int realWidth = width + (i < remainder ? 1 : 0);
            for (int j = 0; j < realWidth; j++) {
                node.next = new ListNode(curr.val);
                node = node.next;
                // 没有 if 判空，OJ 也是能通过的，说明 cur 不可能为空
                // 但是为了 cur 更加严谨，加上 if 判空，避免考虑不周的情况
                // 防御式编程
                if (curr != null) curr = curr.next;
            }
            res[i] = dummyNode.next;
        }
        return res;
    }

    // KeyPoint 方法二 基于原始链表，调整指针实现分隔，不用拷贝节点，节省空间
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode curr = root;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        int width = len / k;
        int remainder = len % k;

        ListNode[] res = new ListNode[k];
        curr = root;
        for (int i = 0; i < k; i++) {
            ListNode head = curr;
            // 不使用 dummyNode 后，每次都从 head 开始，故走的步数需要减 1
            int realWidth = width + (i < remainder ? 1 : 0) - 1;

            // KeyPoint 详细说明：为什么 -1
            // 每一段 curr 需要走的步数比这一段的节点数少 1 个
            // 如：1 -> 2 -> 3 -> 4
            // 链表的长度为 4 ，也就是 4 个节点，但是从第一个节点开始
            // 只需要走 3 步就可以到达最后一个节点

            for (int j = 0; j < realWidth; j++) {
                if (curr != null) curr = curr.next;
            }
            // 跳出 for 循环，cur 在分隔链表的最后
            // bug 修复：判断 curr 不等于 null
            if (curr != null) {
                ListNode next = curr.next;
                curr.next = null;
                // curr 到下一段分隔链表的表头
                curr = next;
            }
            res[i] = head;
        }
        return res;
    }
}
