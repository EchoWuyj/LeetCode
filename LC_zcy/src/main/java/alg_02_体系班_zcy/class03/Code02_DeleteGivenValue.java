package alg_02_体系班_zcy.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 22:36
 * @Version 1.0
 */
public class Code02_DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // head = removeValue(head, 2);
    // 将链表中所有值为2的节点删除
    public static Node removeValue(Node head, int num) {

        if (head == null) {
            return null;
        }

        // 删除的节点有可能是头节点,需要单独判断
        // 不能使用if,因为if是单节点判断,while是多节点循环判断
        while (head != null) {
            if (head.value != num) {
                break;
            }
            // head指针后移,原来的节点不可达,自动释放,也就相当于将其删除
            head = head.next;
        }

        // head来到第一个不需要删的位置

        // 使用pre紧跟着cur
        Node pre = head;
        // 使用cur进行遍历每个节点
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            // cur每次都是往后跳一个位置和if判断无关
            cur = cur.next;
        }
        return head;
    }


    public static void printNode(Node head) {
        if (head == null) {
            return;
        }

        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node6 = new Node(3);
        Node node5 = new Node(2);
        Node node7 = new Node(4);
        Node node4 = new Node(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node6;
        node6.next = node5;
        node5.next = node7;
        node7.next = node4;

        printNode(node1);
        System.out.println("==================");
        Node result = removeValue(node1, 2);
        printNode(result);
    }


}
