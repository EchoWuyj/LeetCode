package alg_03_leetcode_top_zcy.class_03_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 19:09
 * @Version 1.0
 */

// 合并两个有序链表
// 参考 Offer_25_MergeTwoListsOffer_25_MergeTwoLists
public class problem_021_mergeTwoLists {
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {

        // 一开始设置一个虚拟节点，它的值为 -1
        // 它的值可以设置为任何的数，因为我们根本不需要使用它的值
        ListNode dummy = new ListNode(-1);

        // 设置一个指针，指向虚拟节点
        ListNode pre = dummy;

        // 通过一个循环，不断的比较 l1 和 l2 中当前节点值的大小，直到 l1 或者 l2 遍历完毕为止
        while (head1 != null && head2 != null) {
            // 如果 l1 当前节点的值小于等于了 l2 当前节点的值
            if (head1.val <= head2.val) {
                // 让 pre 指向节点的 next 指针指向这个更小值的节点，即指向 l1
                // KeyPoint 一开始pre是在头节点的前一个节点,所以得pre.next指向头节点
                pre.next = head1;
                head1 = head1.next;
            } else {
                // 让 pre 指向节点的 next 指针指向这个更小值的节点，即指向 l2
                pre.next = head2;
                // 让 l2 向后移动
                head2 = head2.next;
            }

            // 让 pre 向后移动， 保证 pre 永远指向链表的最后一个节点
            // KeyPoint 前面是指针，后面是节点 pre -> pre.next
            pre = pre.next;
        }

        // 如果 l1 中还有节点
        if (head1 != null) {
            // 把 l1 中剩下的节点全部加入到 pre 的 next 指针位置
            pre.next = head1;
        }

        // 如果 l2 中还有节点
        if (head2 != null) {
            // 把 l2 中剩下的节点全部加入到 pre 的 next 指针位置
            pre.next = head2;
        }

        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

