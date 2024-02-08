package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 14:48
 * @Version 1.0
 */
public class _25_23_mergeKLists {

    // 合并 K 个升序链表
    // 分治思想
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];
        // 左右边界
        int left = 0, right = lists.length - 1;
        return merge(lists, left, right);
    }

    // 分治方法
    // 递归函数形参：数组，左右边界
    // 递归函数 ListNode，有返回值
    public ListNode merge(ListNode[] lists, int left, int right) {

        // 递的过程(递归边界)
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = left + (right - left) / 2;

        // 左边有序链表
        ListNode leftList = merge(lists, left, mid);
        // 右边有序链表
        // 另外一半从 mid+1 开始，保证两段之间没有重叠
        ListNode rightList = merge(lists, mid + 1, right);
        // 归的过程：合并两个有序链表
        return mergeList(leftList, rightList);
    }

    // 合并两个有序链表
    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        // 串联指针 cur
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
        // 返回 dummy.next
        return dummy.next;
    }
}
