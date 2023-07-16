package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 12:42
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts2 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        int width = len / k;
        int remainder = len % k;

        cur = head;
        ListNode[] res = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode newHead = cur;
            int realWidth = width + ((remainder > i) ? 1 : 0) - 1;
            for (int j = 0; j < realWidth; j++) {
                if (cur != null) cur = cur.next;
            }
            if (cur != null) {
                ListNode next = cur.next;
                cur.next = null;
                cur = next;
            }
            res[i] = newHead;
        }
        return res;
    }
}
