package alg_02_train_wyj._15_day_链表二;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 18:13
 * @Version 1.0
 */
public class _06_445_add_two_numbers_ii {

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode tmp = addTwoNumberHelp(l1, l2);
        return reverse(tmp);
    }

    public ListNode addTwoNumberHelp(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 == null) ? 0 : l1.val;
            int y = (l2 == null) ? 0 : l2.val;

            int sum = x + y + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) cur.next = new ListNode(carry);
        return dummyNode.next;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();

        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode ans = null;
        ListNode cur;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = x + y + carry;
            cur = new ListNode(sum % 10);
            cur.next = ans;
            ans = cur;
            carry = sum / 10;
        }

        if (carry != 0) {
            cur = new ListNode(carry);
            cur.next = ans;
            ans = cur;
        }
        return ans;
    }
}
