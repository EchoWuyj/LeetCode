package alg_02_train_wyj._15_day_链表二;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 19:37
 * @Version 1.0
 */
public class _06_445_add_two_numbers_ii2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Stack<ListNode> stack1 = new Stack<>();
        while (l1 != null) {
            if (l1 != null) stack1.push(l1);
            l1 = l1.next;
        }

        Stack<ListNode> stack2 = new Stack<>();
        while (l2 != null) {
            if (l2 != null) stack2.push(l2);
            l2 = l2.next;
        }

        ListNode res = null;
        ListNode cur;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = x + y + carry;
            cur = new ListNode(sum % 10);
            cur.next = res;
            res = cur;
            carry = sum / 10;
        }
        if (carry != 0) {
            cur = new ListNode(carry);
            cur.next = res;
            res = cur;
        }
        return res;
    }
}
