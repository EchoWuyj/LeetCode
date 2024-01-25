package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 12:08
 * @Version 1.0
 */
public class _49_02_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 两数相加，针对是链表，且是以逆序方式存储
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;

        // 只要有一个链表为不为 null，while 循环则一直进行
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            cur.next = new ListNode(sum % 10);
            // 针对是 sum / 10，不是 carry / 10
            carry = sum / 10;

            // 统一移动指针，否则在原地不移动
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) cur.next = new ListNode(carry);
        return dummy.next;
    }
}
