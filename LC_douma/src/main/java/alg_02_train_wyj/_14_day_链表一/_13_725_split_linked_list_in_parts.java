package alg_02_train_wyj._14_day_链表一;

/**
 * @Author Wuyj
 * @DateTime 2023-05-02 15:15
 * @Version 1.0
 */
public class _13_725_split_linked_list_in_parts {
    public ListNode[] splitListToParts1(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) return res;
        if (root.next == null) {
            res[0] = root;
            return res;
        }
        ListNode cur = root;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        int width = len / k;
        int reminder = len % k;

        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode dummyNode = new ListNode(-1);
            ListNode node = dummyNode;
            int realWidth = width + (reminder > i ? 1 : 0);
            for (int j = 0; j < realWidth; j++) {
                node.next = new ListNode(cur.val);
                node = node.next;
                if (cur != null) cur = cur.next;
            }
            res[i] = dummyNode.next;
        }
        return res;
    }

    public ListNode[] splitListToParts2(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) return res;
        if (root.next == null) {
            res[0] = root;
            return res;
        }
        int len = 0;
        ListNode cur = root;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        int width = len / k;
        int remainder = len % k;

        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = cur;
            int realWidth = width + (remainder > i ? 1 : 0) - 1;
            for (int j = 0; j < realWidth; j++) {
                cur = cur.next;
            }

            if (cur != null) {
                ListNode next = cur.next;
                cur.next = null;
                cur = next;
            }
            res[i] = head;
        }
        return res;
    }
}
