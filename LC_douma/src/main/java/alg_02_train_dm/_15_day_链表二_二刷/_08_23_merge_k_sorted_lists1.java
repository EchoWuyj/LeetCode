package alg_02_train_dm._15_day_链表二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-03 14:37
 * @Version 1.0
 */

public class _08_23_merge_k_sorted_lists1 {

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

    // KeyPoint 方法一 顺序合并 => 先两两合并，合并之后，再两两合并 => 没有超时
    // 时间复杂度：O(k^2 * n)
    // 一次合并 2n，两次合并(2n 节点和 n 节点挨个比较) 3n，...，i 次合并 (i+1)*n
    // 1次合并 ~ (k-1)次合并 => 2n + ... + kn => k^2*n
    // 空间复杂度：O(1)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode outputList = lists[0];
        // k 个链表，需要合并 k-1 次，且 i 从 1 开始
        for (int i = 1; i < lists.length; i++) {
            outputList = merge(outputList, lists[i]);
        }
        return outputList;
    }

    // 时间复杂度：O(2n)
    // => 假设一个每个链表中节点为 n，最坏情况，将两个链表每个节点都要遍历一次，故需要遍历 2n 个节点
    // 空间复杂度：O(1)
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

        // while 循环条件不能丢了
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // KeyPoint curr.next 指向 l1 原来节点，没有创建一个新的节点
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
