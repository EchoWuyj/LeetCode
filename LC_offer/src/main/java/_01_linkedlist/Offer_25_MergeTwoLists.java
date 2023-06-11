package _01_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2022-08-23 13:33
 * @Version 1.0
 */
public class Offer_25_MergeTwoLists {
    public static void main(String[] args) {
        ListNode l1_01 = new ListNode(1);
        ListNode l1_02 = new ListNode(2);
        ListNode l1_04 = new ListNode(4);

        l1_01.next = l1_02;
        l1_02.next = l1_04;

        ListNode l2_01 = new ListNode(1);
        ListNode l2_03 = new ListNode(3);
        ListNode l2_04 = new ListNode(4);

        l2_01.next = l2_03;
        l2_03.next = l2_04;

        ListNode resultNode = mergeTwoLists(l1_01, l2_01);

        print(resultNode);
    }

    public static void print(ListNode listNode) {
        ListNode cur = listNode;

        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 一开始设置一个虚拟节点，它的值为 -1，它的值可以设置为任何的数，因为我们根本不需要使用它的值
        ListNode dummy = new ListNode(-1);

        // 设置一个指针，指向虚拟节点
        ListNode pre = dummy;

        // 通过一个循环，不断的比较 l1 和 l2 中当前节点值的大小，直到 l1 或者 l2 遍历完毕为止
        while (l1 != null && l2 != null) {
            // 如果 l1 当前节点的值小于等于了 l2 当前节点的值
            if (l1.val <= l2.val) {
                // 让 pre 指向节点的 next 指针指向这个更小值的节点，即指向 l1
                pre.next = l1;
                l1 = l1.next;
            } else {
                // 让 pre 指向节点的 next 指针指向这个更小值的节点，即指向 l2
                pre.next = l2;
                // 让 l2 向后移动
                l2 = l2.next;
            }

            // 让 pre 向后移动， 保证 pre 永远指向链表的最后一个节点
            // KeyPoint 前面是指针，后面是节点 pre -> pre.next
            pre = pre.next;
        }

        // 如果 l1 中还有节点
        if (l1 != null) {
            // 把 l1 中剩下的节点全部加入到 pre 的 next 指针位置
            pre.next = l1;
        }

        // 如果 l2 中还有节点
        if (l2 != null) {
            // 把 l2 中剩下的节点全部加入到 pre 的 next 指针位置
            pre.next = l2;
        }

        return dummy.next;
    }
}
