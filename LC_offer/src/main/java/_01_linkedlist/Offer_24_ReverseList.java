package _01_linkedlist;

/**
 * @Author Wuyj
 * @DateTime 2022-08-22 21:01
 * @Version 1.0
 */
public class Offer_24_ReverseList {
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

        ListNode resultNode = reverseList(listNode01);

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

    public static ListNode reverseList(ListNode head) {

        // 寻找递归终止条件
        //  1、head 指向的结点为 null
        //  2、head 指向的结点的下一个结点为 null
        // 在这两种情况下，反转之后的结果还是它自己本身
        if (head == null || head.next == null) {
            return head;
        }

        // 不断的通过递归调用，直到无法递归下去，递归的最小粒度是在最后一个节点
        // 因为到最后一个节点的时候，由于当前节点 head 的 next 节点是空，所以会直接返回 head
        // 因为 5.next == null，达到递归的边界，将返回的 5 赋值给 cur
        ListNode cur = reverseList(head.next);

        // 因为 5.next == null，达到递归的边界，此时递归结束返回上一层，
        // 此时的 head 指针指向的是 4 节点， head.next.next 就是 5.next
        // head.next.next = head 意思就是设置 5 的下一个节点是 4
        head.next.next = head;

        // 同时清除原来 4指向 5的 执政，即 head.next
        head.next = null;

        // 我们把每次反转后的结果传递给上一层
        return cur;
    }
}
