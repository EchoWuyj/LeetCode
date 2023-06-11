package alg_01_ds_dm._03_high_level._01_heap.train;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 15:00
 * @Version 1.0
 */
public class _23_MergeKLinkedLists {
    // 优先队列
    // 时间复杂度：O(k*n*logk)
    // 空间复杂度：O(k)
    public ListNode mergeKLists(ListNode[] lists) { // k 个链表，每个链表长度为 n
        // 底层是 Object[] 实现，申请空间大小为 k，故时间复杂度为 O(k)
        PriorityQueue<ListNode> pq
                // 使用 Lambda 表达式，更加简洁
                = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // O(k*logk)
        for (ListNode node : lists) { // O(k)
            pq.add(node); // O(logk)
        }

        ListNode dummyNode = new ListNode(-1);
        // 指向合并之后链表的最后节点的指针
        ListNode curr = dummyNode;

        // O(kn*logk)
        while (!pq.isEmpty()) { // O(kn)
            ListNode minNode = pq.remove(); // O(logk)
            curr.next = minNode;
            curr = curr.next;

            if (minNode.next != null) {
                pq.add(minNode.next); // O(logk)
            }
        }
        // 最后返回头节点
        return dummyNode.next;
    }

    // 分治思想
    // 时间复杂度：O(k*n*logk)
    // 空间复杂度：O(logk)
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];

        int mid = left + (right - left) / 2;

        ListNode mergedLeftList = merge(lists, left, mid);
        ListNode mergedRightList = merge(lists, mid + 1, right);

        return mergeTwoLists(mergedLeftList, mergedRightList);
    }

    // 顺序合并
    // 时间复杂度：O(k^2 * n)
    // 空间复杂度：O(1)
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode outputList = lists[0];
        for (int i = 1; i < lists.length; i++) {
            outputList = mergeTwoLists(outputList, lists[i]);
        }
        return outputList;
    }

    // 时间复杂度：O(2n)
    // 空间复杂度：O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;

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

class ListNode {
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


