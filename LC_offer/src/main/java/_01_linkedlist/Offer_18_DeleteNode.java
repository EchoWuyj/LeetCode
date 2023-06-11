package _01_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2022-08-22 16:00
 * @Version 1.0
 */
public class Offer_18_DeleteNode {
    public static void main(String[] args) {
        ListNode listNode04 = new ListNode(4);
        ListNode listNode05 = new ListNode(5);
        ListNode listNode01 = new ListNode(1);
        ListNode listNode09 = new ListNode(9);

        listNode04.next = listNode05;
        listNode05.next = listNode01;
        listNode01.next = listNode09;

        // print(listNode04);

        ListNode head = deleteNode(listNode04, 10);

        print(head);
    }

    public static void print(ListNode listNode) {
        ListNode cur = listNode;

        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static ListNode deleteNode(ListNode head, int val) {

        // 特殊情况处理，删除的节点是头结点时，比较特别
        if (head.val == val) return head.next;

        // KeyPoint 链表常用方式

        //设置两个指针，一个指针指向当前的节点
        ListNode pre = head;

        // 一个指针指向当前节点的下一节点
        // KeyPoint cur 指针是从第二个节点开始遍历的
        ListNode cur = head.next;

        // KeyPoint 先通过 while 进行循环判断，到相应的位置，再通过移动指针实现删除功能

        // 当 cur 为空 或 cur 节点值等于 val 时跳出跳出循环
        //  && 有 false 则 false
        // KeyPoint while 循环的判断条件是跳出条件，该条件表示 while 结束
        while (cur != null && cur.val != val) {

            // 两个指针不断的向前移动
            // pre 来到 cur 的位置
            pre = cur;
            // cur 来到下一个节点位置
            cur = cur.next;
        }

        // 相当于覆盖掉了 cur 的节点值
        // KeyPoint 这里删除的节点必然是在链表中的，所以 NullPointerException 并没有考虑到
        pre.next = cur.next;
        // 最后返回链表头结点就行
        return head;
    }
}
