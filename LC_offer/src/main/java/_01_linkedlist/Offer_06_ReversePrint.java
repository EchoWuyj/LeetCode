package _01_linkedlist;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-08-21 13:00
 * @Version 1.0
 */
public class Offer_06_ReversePrint {

    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int vale) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {

        // head = [1,3,2]
        ListNode listNode01 = new ListNode(1);
        ListNode listNode03 = new ListNode(3);
        ListNode listNode02 = new ListNode(2);

        listNode01.next = listNode03;
        listNode03.next = listNode02;

        // 定义当前指针
        ListNode curNode = listNode01;

        // 遍历
        while (curNode != null) {
            System.out.print(curNode.val + " ");
            curNode = curNode.next;
        }

        System.out.println();

        int[] result = reversePrint(listNode01);

        // result.toString() 还是地址值
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] reversePrint(ListNode head) {

        /*
        KeyPoint JAVA集合类对应的栈（Stack）
             向栈中存放元素：stack.push()
             获取栈顶元素：stack.peek()
             删除栈顶元素(返回值为删除的元素)：stack.pop()
         */

        // 构建一个栈，用来存储链表中每个节点的值
        Stack<Integer> stack = new Stack<>();

        // 构建一个指针，指向链表的头结点位置，从它开始向后遍历
        // KeyPoint 类似于 int i = 3; 基本数据结构可以直接使用，不用去 new
        ListNode curNode = head;

        // 不断的遍历原链表中的每个节点，直到为 null
        while (curNode != null) {
            // 把每个节点的值加入到栈中
            stack.push(curNode.val);
            // curNode 向后移动
            curNode = curNode.next;
        }
        // 获取栈的长度
        int size = stack.size();

        // 通过栈的长度，定义一个同样长度的数组 res
        int[] arr = new int[size];

        // KeyPoint 涉及数组的索引下标，则是不能使用增强 for 循环，因为在遍历过程中得使用到索引 i
        for (int i = 0; i < arr.length; i++) {

            // 数组接收栈顶元素值，这里使用 pop，接受元素之后需要将其删除，故不能使用 peek
            arr[i] = stack.pop();
        }

        // 最后返回结果数组就行
        return arr;
    }
}
