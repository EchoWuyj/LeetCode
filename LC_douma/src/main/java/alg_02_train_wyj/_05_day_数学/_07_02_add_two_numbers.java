package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:32
 * @Version 1.0
 */
public class _07_02_add_two_numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 结果链表的新表头
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        int carry = 0;
        // 说明有 l1 或者 l2 有节点，则一直执行循环
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            // 两个链表从个位开始相加计算，最后输出的链表也是从个位到高位
            // pre 为当前计算位节点 new ListNode 前一个节点
            pre.next = new ListNode(sum % 10);
            pre = pre.next;
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) {
            pre.next = new ListNode(1);
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


