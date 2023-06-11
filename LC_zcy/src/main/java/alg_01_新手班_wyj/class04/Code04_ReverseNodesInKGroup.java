package alg_01_新手班_wyj.class04;

/**
 * @Author Wuyj
 * @DateTime 2022-09-09 15:03
 * @Version 1.0
 */
public class Code04_ReverseNodesInKGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);

        // 第一组都凑不齐
        if (end == null) {
            return head;
        }

        head = end;
        reverse(start, end);

        ListNode lastEnd = start;

        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);

            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }


    public static void reverse(ListNode start, ListNode end) {

        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
