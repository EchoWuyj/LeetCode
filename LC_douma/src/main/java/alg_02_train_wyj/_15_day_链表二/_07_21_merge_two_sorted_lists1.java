package alg_02_train_wyj._15_day_链表二;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 20:03
 * @Version 1.0
 */
public class _07_21_merge_two_sorted_lists1 {
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val){
                cur.next = list1;
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
