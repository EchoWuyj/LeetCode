package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:48
 * @Version 1.0
 */
public class _25_23_mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];
        return merge(lists, 0, lists.length - 1);
    }

    // 分治方法
    // 递归函数形参，数组，左右边界
    public ListNode merge(ListNode[] lists, int left, int right) {

        // 递的规程
        if (left == right) return lists[left];
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        ListNode leftList = merge(lists, left, mid);
        // 另外一半从 mid+1 开始，保证两段之间没有重叠
        ListNode rightList = merge(lists, mid + 1, right);
        // 归的过程：合并
        return mergeList(leftList, rightList);
    }

    // 合并链表
    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 同时不为 null，执行 while 循环
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // 移动 cur 指针，进行串联
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if (list1 == null) cur.next = list2;
        if (list2 == null) cur.next = list1;
        return dummy.next;
    }
}
