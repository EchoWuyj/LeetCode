package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:36
 * @Version 1.0
 */
public class _06_445_add_two_numbers_ii1 {

      /*
          445. 两数相加 II
          给你两个 非空 链表来代表两个非负整数。
          数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
          将这两数相加会返回一个新的链表。
          你可以假设除了数字 0 之外，这两个数字都不会以零开头。

          示例 1：
          输入：l1 = [7,2,4,3], l2 = [5,6,4]
          输出：[7,8,0,7]

           高位        低位
            ↓           ↓
           head        tail
            ↓           ↓
            7 → 2 → 4 → 3
              + 5 → 6 → 4
          ----------------
            7   8   0   7   ← 运算顺序

          进阶：如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

     */

    // KeyPoint 方法一 反转解法 ，空间复杂度 O(1)
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        // 直接使用 l1 和 l2 接受，不创建新的变量
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode retNode = add(l1, l2);
        return reverse(retNode);
    }

    // 辅助方法，方法名 Help
    public ListNode add(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            curr.next = new ListNode(sum % 10);

            // KeyPoint 注意事项
            // cur 是遍历遍历指针，一定需要移动
            // KeyPoint 防御式编程
            // 凡是涉及指针，多想一步，是否将其移动
            curr = curr.next;
            carry = sum / 10;

            // l1，l2 不为 null
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            // KeyPoint 写完代码检查 code view
            // 1.以后写完代码一定需要 code view
            //   操作方式：鼠标选中代码一遍，避免低级错误
            // 2.链表超时，多半是指针出了问题
            //   指针没有移动，或者形成环路，多排查链接指针的操作
            // 3.写代码，一定需要仔细，避免不必要的 bug，提高效率
        }
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
