package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:15
 * @Version 1.0
 */
public class _09_21_mergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // 特判
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // 虚拟节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                // 同时后移
                list1 = list1.next;
                cur = cur.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
            }
        }

        if (list1 == null) cur.next = list2;
        if (list2 == null) cur.next = list1;
        return dummy.next;
    }
}
