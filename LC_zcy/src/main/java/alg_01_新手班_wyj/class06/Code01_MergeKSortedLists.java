package alg_01_新手班_wyj.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 10:04
 * @Version 1.0
 */
public class Code01_MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        PriorityQueue<ListNode> minheap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minheap.add(lists[i]);
            }
        }

        if (minheap.isEmpty()) {
            return null;
        }

        // 头节点
        ListNode head = minheap.poll();
        ListNode pre = head;

        if (pre.next != null) {
            minheap.add(pre.next);
        }

        while (!minheap.isEmpty()) {
            ListNode cur = minheap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                minheap.add(cur.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {

    }
}
