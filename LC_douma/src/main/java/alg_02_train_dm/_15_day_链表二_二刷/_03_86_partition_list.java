package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 20:38
 * @Version 1.0
 */
public class _03_86_partition_list {
    /*
       86. 分隔链表
       给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
       使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

       你应当 保留 两个分区中每个节点的初始相对位置。

       示例 1：
       输入：head = [1,4,3,2,5,2], x = 3
       输出：[1,2,2,4,3,5]

       示例 2：
       输入：head = [2,1], x = 2
       输出：[1,2]

       提示：
       链表中节点的数目在范围 [0, 200] 内
       -100 <= Node.val <= 100
       -200 <= x <= 200

    */

    // KeyPoint 方法一
    // 开辟新的链表 new ListNode(...) => 空间复杂度 O(n)
    // 1. < x => 创建一个链表
    // 2. >= x => 创建一个链表
    // 3. 将两个链表串联

    // KeyPoint 方法二
    // 不创建链表节点，通过修改链表节点指针实现 => 原地解法
    // 明确要点 => 链表考察的点：虚拟节点 + 链表指针的移动
    public ListNode partition(ListNode head, int x) {

        //  1 → 4 → 3 → 2 → 5 → 2 → null，x = 3
        //  ↑
        // head (遍历指针)

        // smallHead (虚拟节点)  → 1 → 2 → 2
        //                            ↑
        //                         small (遍历指针)
        // largeHead (虚拟节点)  → 4 → 3 → 5 → null
        //                            ↑
        //                          large (遍历指针)

        if (head == null || head.next == null) return head;
        // 定义 small 和 large 进行串联指针，不用创建新的链表节点
        ListNode smallHead = new ListNode(-1);
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(-1);
        ListNode large = largeHead;

        // head 遍历原链表指针
        while (head != null) {
            if (head.val < x) {
                // KeyPoint 注意：small.next = head 不是创建 head 节点副本
                // 只是将 small 节点的 next 指针赋值为 head 节点的引用
                // => small.next 和 head 都将指向内存中的同一个节点
                // => 并没有创建新的节点，只是通过调整指针的方式将现有的节点重新连接起来
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        // head 遍历到链表结尾节点，large 和 small 指针需要最后调整指针
        // 即断链和串联，保证两部分链表能正确相链接

        // KeyPoint 断链
        // 因为本题使用调整指针方式，没有创建 head 节点的副本
        // 故在串联时，需要将最后一个 large 节点的 next 指针置为空
        // 否则其 next 可能还指向下个节点，如： 5 → 2 → null
        large.next = null;
        // 串联：小到大顺序
        small.next = largeHead.next;

        return smallHead.next;
    }
}
