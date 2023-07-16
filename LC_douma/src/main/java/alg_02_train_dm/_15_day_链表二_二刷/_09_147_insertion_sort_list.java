package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:38
 * @Version 1.0
 */

public class _09_147_insertion_sort_list {

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

        示例 1：
        输入: head = [4,2,1,3]
        输出: [1,2,3,4]

        示例 2：
        输入: head = [-1,5,3,4,0]
        输出: [-1,0,3,4,5]

        提示
        列表中的节点数在 [1, 5000]范围内
        -5000 <= Node.val <= 5000

     */

    // 将未排序数组每个元素，插入到已经有序数组中合适位置
    // 1.将数组分成已排序区间和未排序区间
    // 2.循环遍历未排序区间中每个元素，在已排序区间中找到应该在位置，然后将其插入
    // 3.以此往复
    // KeyPoint 注意：链表转数组，然后 sort，然后转换为链表， 面试的时候不能这么干！
    // 时间辅助度 O(n^2)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 链表中节点有可能插入到表头前面，相当于在表头新增节点，故需要使用 dummy
        // => 一般新增和删除节点，都是需要使用 dummy 的
        ListNode dummy = new ListNode(-1);

        // 需要将 dummy 和 head 串联起来
        // => 保持代码书写的严格步骤性，相关逻辑代码放到一起，而不是东写一点，西写一点
        dummy.next = head;
        // 插入排序中，默认第一个节点有序，故 cur 从第二节点开始
        ListNode cur = head.next;
        // 前一个节点
        ListNode pre = head;
        while (cur != null) {
            // cur >= pre => 升序 => 不用调整，两指针往后移
            if (cur.val >= pre.val) {
                // pre 先移动，cur 再移动
                pre = cur;
                cur = cur.next;
            } else {
                // cur.val < pre.val
                // => cur 应该插入第一个大于 cur 的节点的前一个节点
                // => 使用 p 指针记录，并且是从 dummy 开始完后遍历
                ListNode p = dummy;

                // 1.通过 while 循环，移动 p.next 指针到第一个大于 cur 的节点
                //   => 即 p 为 cur 节点应该插入位置
                // 2.while 循环是用 p.next 作为判断条件
                //   若跳出 while 循环，说明 p.next.val >= cur.val，而 p.val < cur.val
                //   此时，p 为第一个大于 cur 的节点的前一个节点
                // 3.保证程序严谨性，加上 p.next != null
                while (p.next != null && p.next.val < cur.val) {
                    p = p.next;
                }
                // KeyPoint 补充说明：p.next != null
                // while 循环中的 p.next 不可能为空，因为 p 从头开始，
                // 最远能到 cur 的前一个节点，所以 p.next 不可能为 null
                // => 这里只是保证程序严谨性，才加上 p.next 的判空

                // KeyPoint 链表指针调整的关键
                // 1.避免断链
                // 2.指针是否已经移动(修改)过
                //   => 移动指针，一定讲究先后顺序
                //   => 若指针移动过，则不是原来位置

                // KeyPoint 找语义准确的指针
                // pre 表示 cur 前一个指针，时刻都是成立，其语义是最准确的
                // => 必然有 pre.next = cur
                // 只是在这种情况下： p → pre → cur，则 p.next.next = cur
                // => 若 p 距离 cur 很远，则 p.next.next 不是 cur
                // => p 指针最准确语义定义：记录的是第一个大于 cur 的节点

                // -1 → -1 → 5 → 3 → 4 → 0 → null
                //       ↑   ↑   ↑   ↑
                //       p  pre cur cur.next

                // -1 → -1 → 3 → 5 → 4 → 0 → null
                //       ↑   ↑   ↑   ↑
                //       p  cur pre cur.next
                //                   ↑
                //                  cur
                // 后续再执行循环，再去比较 pre.val 和 cur.val，执行相应的分支操作

                // KeyPoint 交换指针
                // 将 cur 插入到 p 和 p.next 之间
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;

                // KeyPoint 指针前移
                // cur 再次指向 pre 后面一个节点，处理下个节点
                cur = pre.next;
            }
        }
        return dummy.next;
    }

    // KeyPoint 解决 bug 经验(一)
    // 遇到 bug，先初步看代码是否有明显错误，实在没有看出来，
    // 再去 debug，不要上来就去 debug，需要学会 CodeView，提高效率

    // KeyPoint 解决 bug 经验(二)
    // 从整体到局部，最后再细化到某行代码，而不是一上来就抓细节，浪费时间
    // 1.先通过一次循环结果，分析结果是否达到该循环目的
    // 2.若结果没有问题，则跳过细节，有问题，再去详细分析哪一步错了
}
