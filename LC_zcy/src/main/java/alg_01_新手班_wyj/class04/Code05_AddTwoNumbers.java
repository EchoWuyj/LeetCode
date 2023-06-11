package alg_01_新手班_wyj.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-09 14:20
 * @Version 1.0
 */
public class Code05_AddTwoNumbers {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int L1 = getLength(head1);
        int L2 = getLength(head2);

        ListNode curL = (L1 >= L2) ? head1 : head2;
        ListNode curS = (curL == head1) ? head2 : head1;

        ListNode l = curL;

        int carry = 0;
        int carryNum;

        ListNode last = curL;

        //
        while (curS != null) {
            carryNum = curL.val + curS.val + carry;
            curL.val = carryNum % 10;
            carry = carryNum / 10;

            last = curL;
            curL = curL.next;
            curS = curS.next;
        }

        while (curL != null) {
            carryNum = curL.val + carry;
            curL.val = carryNum % 10;
            carry = carryNum / 10;

            last = curL;
            curL = curL.next;
        }

        if (carry != 0) {
            last.next = new ListNode(1);
        }

        return l;
    }

    public int getLength(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }
}
