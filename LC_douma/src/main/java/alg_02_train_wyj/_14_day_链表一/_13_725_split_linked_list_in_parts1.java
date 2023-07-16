package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 15:15
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts1 {
    public ListNode[] splitListToParts1(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int subLen = len / k;
        int remainder = len % k;
        ListNode[] res = new ListNode[k];

        cur = head;
        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(-1);
            ListNode node = dummy;
            int width = subLen + ((remainder > i) ? 1 : 0);
            for (int j = 0; j < width; j++) {
                node.next = new ListNode(cur.val);
                node = node.next;
                cur = cur.next;
            }
            res[i] = dummy.next;
        }
        return res;
    }
}
