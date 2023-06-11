package algorithm._05_linked_list.atguigu;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-17 9:34
 * @Version 1.0
 */
public class LeetCode_19_RemoveNthNodeFromEnd {

    // 方法一:计算链表长度,需要进行两次遍历
    public static ListNode removeNthFromEnd01(ListNode head, int n) {
        // 1.遍历链表,得到长度l
        int l = getLength(head);

        // 2.从前到后继续遍历,找到正数第l-n+1个元素,该元素为倒数第n个元素
        //  但是在链表中需要找到第l-n个元素,通过移动l-n个元素的next指针,从而将l-n+1个元素删除

        // 避免头节点单独处理的情况(直接删除head节点的情况),定义一个哨兵节点,next指向头节点
        ListNode sentinel = new ListNode(-1, head);

        // 当前节点的指针
        ListNode curr = sentinel;

        // 类比数组理解:第l-n个元素,对应的索引坐标为l-n-1,所以i<l-n
        for (int i = 0; i < l - n; i++) {
            curr = curr.next;
        }

        // 找到第l-n个节点,跳过第l-n+1个节点
        curr.next = curr.next.next;

        // sentinel.next指向head节点
        return sentinel.next;
    }

    // 定义一个计算链表长度的方法
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // 方法二:即从后往前寻找就是反向,即后进先出,使用栈来解决
    // 先从前到后将所有的元素都进栈,然后再去出栈n个元素
    public static ListNode removeNthFromEnd02(ListNode head, int n) {
        // 定义一个哨兵节点,next指向头节点
        ListNode sentinel = new ListNode(-1, head);

        ListNode curr = sentinel;

        // 定义一个栈
        Stack<ListNode> stack = new Stack<>();

        // 1.将所有节点入栈
        while (curr != null) {
            // 将sentinel也进栈,这样即使链表的第一个元素出栈,栈里面也不会为空
            stack.push(curr);
            curr = curr.next;
        }

        // 2.弹栈n次
        for (int i = 0; i < n; i++) {
            // stack中存储为引用,链表的前后关系还是存在的,弹栈只是从栈中出来
            stack.pop();
        }
        // stack的peek方法是返回栈顶的元素但不移除它,stack的pop方法是会移除的
        stack.peek().next = stack.peek().next.next;

        return sentinel.next;
    }

    // 方法三:双指针
    public static ListNode removeNthFromEnd03(ListNode head, int n) {
        // 定义一个哨兵节点,next指向头节点
        ListNode sentinel = new ListNode(-1, head);

        // 定义前后双指针
        ListNode first = sentinel, second = sentinel;

        // 1.若first和second之间的间距为n,当first指向为null,second指向为倒数第n个节点
        //  而删除倒数第n个节点需要知道前一个节点,故first先走n+1步
        for (int i = 0; i < n + 1; i++) {
            // 由哨兵节点开始,走一步就是第一个节点,走n+1步,就是n+1节点
            first = first.next;
        }

        // 2.first,second同时前进,当first变为null,second就是倒数第n+1个节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 3.删除倒数第n个节点
        second.next = second.next.next;

        return sentinel.next;
    }

    public static void printList(ListNode head) {
        ListNode curNode = head;
        while (curNode != null) {
            System.out.print(curNode.val + "->");
            // KeyPoint 当前指针->下一个节点
            //  在链表问题中的赋值操作,前面表示的为指针,后面表示的为节点
            curNode = curNode.next;
        }
        // 最后是以null结尾
        System.out.print("null");
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        TestLinkedList.printList(listNode1);
        LeetCode_19_RemoveNthNodeFromEnd removeNthNodeFromEnd = new LeetCode_19_RemoveNthNodeFromEnd();
        TestLinkedList.printList(removeNthNodeFromEnd.removeNthFromEnd03(listNode1, 2));
    }
}
