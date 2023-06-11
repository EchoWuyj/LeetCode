package algorithm._05_linked_list.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-03-16 10:31
 * @Version 1.0
 */
public class TestLinkedList {
    public static void main(String[] args) {
        // 构建一个链表,把所有节点创建出来,然后连接
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(17);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = null;

        printList(listNode1);
    }

    // 通过头节点传入表示当前的list
    public  static void printList(ListNode head) {
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
}
