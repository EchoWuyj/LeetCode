package alg_01_ds_wyj._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-05-01 10:56
 * @Version 1.0
 */
public class _06_23_MergeKLinkedLists {

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode output = lists[0];
        for (int i = 1; i < lists.length; i++) {
            output = mergeTwoLists(output, lists[i]);
        }
        return output;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 == null) cur.next = l2;
        if (l2 == null) cur.next = l1;

        return dummyNode.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        ListNode leftMergedList = merge(lists, left, mid);
        ListNode rightMergedList = merge(lists, mid + 1, right);

        return mergeTwoLists(leftMergedList, rightMergedList);
    }
}
