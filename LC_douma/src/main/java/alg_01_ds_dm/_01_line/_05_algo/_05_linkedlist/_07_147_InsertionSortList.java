package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 14:58
 * @Version 1.0
 */
public class _07_147_InsertionSortList {

    /*
        147. 对链表进行插入排序
        给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。

        插入排序 算法的步骤:
        1.插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
        2.每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
        3.重复直到所有输入数据插入完为止。

        下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。
        每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。

        对链表进行插入排序。

        提示
        列表中的节点数在 [1, 5000]范围内
        -5000 <= Node.val <= 5000

        KeyPoint 链表转数组，然后sort，然后转换为链表， 面试的时候不能这么干！

     */

    // 时间辅助度 O(n^2)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 链表中节点有可能插入到表头前面，相当于在表头新增节点，故需要使用 dummyNode
        // KeyPoint 一般新增和删除节点，都是需要使用 dummyNode 的
        ListNode dummyNode = new ListNode(-1);
        // KeyPoint 需要将 dummyNode 和 head 串联起来
        //          => 保持代码书写的严格步骤性，相关逻辑代码放到一起，而不是东写一点，西写一点
        dummyNode.next = head;
        ListNode prev = head;
        // 插入排序中，默认第一个节点有序，故 curr 从第二节点开始
        ListNode curr = head.next;
        while (curr != null) {
            // curr >= prev 升序，不用调整，两指针往后移
            if (curr.val >= prev.val) {
                // prev 先移动，curr 再移动
                prev = curr;
                curr = curr.next;
            } else {
                // curr.val < prev.val
                // 记录第一个大于 cur 的节点，从 dummyNode 开始完后遍历
                ListNode p = dummyNode;

                // cur 节点应该插入到第一个大于 cur 的节点
                // p 一旦跳出 while 循环，p 指针指向的就是该节点
                // p.next 到不满足位置，跳出 while 循环，p 则是第一满足位置
                while (p.next != null && p.next.val < curr.val) {
                    p = p.next;
                    // KeyPoint 补充说明
                    // 这里的 p.next 不可能为空，因为 p 从头开始
                    // 最远可以到达的节点是 curr 的前一个节点，所以 p.next 不可能为 null
                    // 我这里加上 p.next 的判空，是我个人的习惯哟~
                    // => 程序严谨性，养成这种习惯
                }

                // 将 curr 插入到 p 和 p.next 之间
                // KeyPoint 移动指针，一定讲究先后顺序 => 若指针移动过，则不是原来位置

                // KeyPoint 找语义准确的指针，
                // 如：prev 才表示 cur 前一个指针，虽然有些情况下，p.next.next = prev.next
                // 但是 p 记录的是第一个大于 cur 的节点，若 p 距离 cur 很远，则 p.next.next 不是 prev
                prev.next = curr.next;
                curr.next = p.next;
                p.next = curr;

                // curr 再次指向 prev 后面一个节点，处理下个节点
                curr = prev.next;

                // KeyPoint 遇到 bug，先初步看代码是否有明显错误，实在没有看出来，再去 debug，不要上来就去 debug
                //          需要学会 CodeView
                // KeyPoint 调试代码 => 从整体到局部，最后再细化到某行代码，而不是一上来就抓细节，浪费时间
                //          => 先通过一次循环结果，分析结果是否达到该循环目的
                //          => 若结果没有问题，则跳过细节，有问题，再去详细分析哪一步错了

                // System.out.println(dummyNode.next);
            }
        }
        return dummyNode.next;
    }
}
