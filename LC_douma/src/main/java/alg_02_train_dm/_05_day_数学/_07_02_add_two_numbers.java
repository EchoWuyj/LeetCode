package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:22
 * @Version 1.0
 */

public class _07_02_add_two_numbers {
    /*
        2. 两数相加
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的
        并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以 相同形式 返回一个表示和的链表。
        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        示例 1：
        输入：l1 = [2,4,3], l2 = [5,6,4]
        输出：[7,0,8]
        解释：342 + 465 = 807.

           2 → 4 → 3
        +  5 → 6 → 4
          -----------
           7 → 0 → 8

           342
         + 465
         -----
           807

        提示：
        每个链表中的节点数在范围 [1, 100] 内
        0 <= Node.val <= 9
        题目数据保证列表表示的数字不含前导零

     */

    // 套用代码模板
    // => 对于链表，只能从链表表头开始往后遍历，同时链表表头也是个位数，正好符合两数相加规则
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果链表的新表头
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        int carry = 0;
        // 说明有 l1 或者 l2 有节点
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int total = x + y + carry;
            // 两个链表从个位开始相加计算，最后输出的链表也是从个位到高位
            // pre 为当前计算位节点 new ListNode 前一个节点
            pre.next = new ListNode(total % 10);
            carry = total / 10;
            // pre 指针后移
            pre = pre.next;
            // l1 和 l2 后移，需要判空才能后移
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) pre.next = new ListNode(carry);
        // 最后也是逆序方式，返回链表头节点
        // dummy → 7 → 0 → 8 → pre
        return dummy.next;
    }
}

