package alg_02_train_dm._15_day_链表二;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:36
 * @Version 1.0
 */
public class _06_445_add_two_numbers_ii {

      /*
          445. 两数相加 II
          给你两个 非空 链表来代表两个非负整数。
          数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
          将这两数相加会返回一个新的链表。

          你可以假设除了数字 0 之外，这两个数字都不会以零开头。

          示例 1：
          输入：l1 = [7,2,4,3], l2 = [5,6,4]
          输出：[7,8,0,7]

          进阶：
          如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     */

    // KeyPoint 方法一 反转解法 ，空间复杂度 O(1)
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 直接使用 l1 和 l2 接受，不创建新的变量
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode retNode = addTwoNumbersHelp(l1, l2);

        return reverse(retNode);
    }

    // 辅助方法，方法名 Help
    public ListNode addTwoNumbersHelp(ListNode l1, ListNode l2) {
        // 课程 A -》 链表 -> 基础篇
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            curr.next = new ListNode(sum % 10);
            // KeyPoint cur 是遍历遍历指针，一定需要移动
            // 凡是涉及指针，多想一步，是否将其移动，防御式编程
            curr = curr.next;
            carry = sum / 10;

            // l1，l2 不为 null
            // KeyPoint 以后写完代码一定需要 code view，鼠标选中代码一遍，避免低级错误
            // KeyPoint 链表超时，多半是指针出了问题，没有移动，或者形成环路，多排查链接指针的操作
            // KeyPoint 写代码，一定需要仔细，避免不必要的 bug，提高效率
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // KeyPoint 方法二 使用栈
    // 链表表头表示数字高位，在数字相加中无法先运算，故先将其暂存，后面再操作 => 先进后出 => 栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 将 l1 所有节点放到栈中
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        // 将 l2 所有节点放到栈中
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        // 结果链表头指针
        ListNode ans = null;

        // 用于存储两数相加时的进位
        ListNode curr;

        int carry = 0;
        // 只要有一个栈不为空，就循环执行代码
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // 若 stack 中没有元素，pop 操作会抛出异常，而不是 null
            // 所以在 pop 操作之前，先进行判空操作
            // stack1.pop() 结果是 ListNode，但是要的是 val，不是 ListNode
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;

            int sum = x + y + carry;

            // 将计算的结果放在链表表尾，避免再次反转
            curr = new ListNode(sum % 10);
            curr.next = ans;
            ans = curr;

            carry = sum / 10;
        }

        if (carry != 0) {
            curr = new ListNode(carry);
            curr.next = ans;
            ans = curr;
        }

        return ans;
    }
}
