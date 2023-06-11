package _01_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2022-08-22 20:34
 * @Version 1.0
 */
public class Offer_22_GetKthFromEnd {
    public static void main(String[] args) {
        ListNode listNode01 = new ListNode(1);
        ListNode listNode02 = new ListNode(2);
        ListNode listNode03 = new ListNode(3);
        ListNode listNode04 = new ListNode(4);
        ListNode listNode05 = new ListNode(5);

        listNode01.next = listNode02;
        listNode02.next = listNode03;
        listNode03.next = listNode04;
        listNode04.next = listNode05;

        print(listNode01);

        ListNode resultNode = getKthFromEnd(listNode01, 3);

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

    public static ListNode getKthFromEnd(ListNode head, int k) {
        // KeyPoint 快慢指针
        //  先让快指针走k步，然后两个指针同步走
        //  当快指针走到头时，慢指针就是链表倒数第k个节点

        //初始化两个指针 former 和 latter，一开始都指向链表的头节点
        // 指针 former 指向链表的头节点
        ListNode former = head;
        // 指针 latter 指向链表的头节点
        ListNode latter = head;

        // 让 former 指针先向前走 k 步
        for (int i = 0; i < k; i++) {
            // former 指针向后移动
            former = former.next;
        }

        // 接下来，让这两个指针 former 和 latter 同时向前移动，直到前指针 former 指向 NULL
        while (former != null) {
            // former 指针向后移动
            former = former.next;
            // latter 指针向后移动
            latter = latter.next;
        }

        // 此时，由于 former 和 latter 之间的距离为 k
        // 所以 latter 指向的节点即是倒数第 k 个节点
        return latter;
    }
}
