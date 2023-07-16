package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-14 21:31
 * @Version 1.0
 */
public class _08_23_merge_k_sorted_lists2 {

    // KeyPoint 方法二 分治思想 => 递归实现
    // 时间复杂度：O(k*n*logk)
    // => 假设 ListNode[] lists 一共有 k 个链表，每个链表平均有 n 个节点
    // => 递归树高度 logk，且递归树每层都需要遍历节点数 kn => logk * k * n
    // 空间复杂度：O(logk) => 系统栈深度 = 递归树高度 => logk
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length - 1);
    }

    // 对链表的某个区间 [left,right] 进行合并，返回值为 [left,right] 合并之后的头节点
    // 代码实现上和归并排序一样
    private ListNode merge(ListNode[] lists, int left, int right) {

        // 划分区间，每次一分为二，直到最小子问题，即只有一个链表
        //     l1 l2 l3  l4 l5 l6
        //       ↙         ↘
        //   l1 l2 l3     l4 l5 l6
        //   ....

        // 区间只有一个链表，直接返回该链表即可
        if (left == right) return lists[left];
        // 区间不成立，返回 null 即可
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        // merge 合并左区间链表 => 合并好的左链表 leftList
        ListNode leftList = merge(lists, left, mid);
        // merge 合并右区间链表 => 合并好的右链表 rightList
        // KeyPoint left = mid+1
        ListNode rightList = merge(lists, mid + 1, right);

        // 将 leftList 和 rightList 进行合并
        return mergeTwoLists(leftList, rightList);
    }

    // 时间复杂度：O(2n)
    // => 假设一个每个链表中节点为 n，最坏情况，将两个链表每个节点都要遍历一次，故需要遍历 2n 个节点
    // 空间复杂度：O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        // while 循环条件不能丢了
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return dummyNode.next;
    }
}
