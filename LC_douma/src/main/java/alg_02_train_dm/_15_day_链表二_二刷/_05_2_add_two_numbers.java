package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:36
 * @Version 1.0
 */
public class _05_2_add_two_numbers {
    
    /* 
      2. 两数相加
      给你两个 非空 的链表，表示两个非负的整数。
      它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
      请你将两个数相加，并以相同形式返回一个表示和的链表。

      示例 1：
      输入：l1 = [2,4,3], l2 = [5,6,4]
      输出：[7,0,8]
      解释：342 + 465 = 807.

                   低位    高位
                    ↓       ↓
                   head    tail
                    ↓       ↓
                    2 → 4 → 3
                  + 5 → 6 → 4
                  ------------
       运算顺序 →    7   0   8

      提示：
      每个链表中的节点数在范围 [1, 100] 内
      0 <= Node.val <= 9
      题目数据保证列表表示的数字不含前导零

   */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果链表的新表头
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int carry = 0;
        // 对于链表，只能从表头往后遍历，且该链表表头又是个位数，两数相加都是从个位数开始，恰好符合
        // 说明有 l1 或者 l2 有节点，则一直执行循环
        while (l1 != null || l2 != null) {
            // l1 或者 l2 为 null，使用 0 替代，使用 null 相加没有意义
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            // 两个链表从个位开始相加计算，最后输出的链表也是从个位到高位
            // 连接链表各个节点
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            carry = sum / 10;

            // 链表不为空，指针往后移动
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // 题目要求不能包含前导 0，故需要对 carry 进行判断
        // 若 carry 不为 0，才将链表的高位添加 carry
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }
}
