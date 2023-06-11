package alg_01_ds_dm._01_line._05_algo._05_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2023-04-30 13:22
 * @Version 1.0
 */
public class _06_23_MergeKLinkedLists {



    /*
        23. 合并 K 个升序链表
        给你一个链表数组，每个链表都已经按升序排列。
        请你将所有链表合并到一个升序链表中，返回合并后的链表。

        示例 1：
        输入：lists = [[1,4,5],[1,3,4],[2,6]]
        输出：[1,1,2,3,4,4,5,6]
        解释：链表数组如下：
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        将它们合并到一个有序链表中得到。
        1->1->2->3->4->4->5->6

        提示
        k == lists.length
        0 <= k <= 10^4
        0 <= lists[i].length <= 500
        -10^4 <= lists[i][j] <= 10^4
        lists[i] 按 升序 排列
        lists[i].length 的总和不超过 10^4

     */

    // KeyPoint 方法一 顺序合并 => 先两两合并，合并之后，再两两合并
    // 时间复杂度：O(k^2 * n)
    // 一次合并 2n，两次合并 3n，...，i 次合并 (i+1)n =>  k^2*n
    // 空间复杂度：O(1)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode outputList = lists[0];
        // k 个链表，需要合并 k-1 次
        for (int i = 1; i < lists.length; i++) {
            outputList = mergeTwoLists(outputList, lists[i]);
        }
        return outputList;
    }

    // 时间复杂度：O(2n) => 最坏情况，将两个链表每个节点都要遍历一次
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

    // KeyPoint 方法二 分治思想 => 递归实现
    // 时间复杂度：O(k*n*logk) => 递归树高度 logk，每层遍历节点数 kn
    // 空间复杂度：O(logk) => 系统栈深度 = 递归树高度 => logk
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length - 1);
    }

    // 对链表的某个区间 [left,right] 进行合并，返回值为 [left,right] 合并之后的头节点
    // 代码实现上和归并排序一样
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 区间只有一个链表，直接该链表即可
        if (left == right) return lists[left];
        // 区间不成立，返回 null 即可
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        // merge 合并左区间链表 => 合并好的左链表 mergedLeftList
        ListNode mergedLeftList = merge(lists, left, mid);
        // merge 合并右区间链表 => 合并好的右链表 mergedRightList
        ListNode mergedRightList = merge(lists, mid + 1, right);

        // 将 mergedLeftList 和 mergedRightList 进行合并
        return mergeTwoLists(mergedLeftList, mergedRightList);
    }
}
