package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 16:34
 * @Version 1.0
 */
public class _05_2_add_two_numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 == null) ? 0 : l1.val;
            int y = (l2 == null) ? 0 : l2.val;
            int sum = x + y + carry;
            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;
            cur.next = newNode;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            cur = cur.next;
        }
        if (carry != 0) cur.next = new ListNode(carry);
        return dummy.next;
    }
}
