package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:32
 * @Version 1.0
 */
public class _07_02_add_two_numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            pre.next = new ListNode((sum) % 10);
            pre = pre.next;
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) pre.next = new ListNode(carry);
        return dummy.next;
    }
}


