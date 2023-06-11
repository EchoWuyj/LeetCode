package alg_02_体系班_zcy.class09;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:18
 * @Version 1.0
 */
public class Code03_SmallerEqualBigger {

    // 给定一个单链表的头节点head,给定一个整数n
    // 将链表按n划分成左边<n,中间==n,右边>n
    // 左右区域不能保证内部有序,不能进行严格从小到大排序

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一:使用容器(数组)
    // 将链表数据存储到数组中(这里直接建立Node数组),然后用快排的思路(荷兰国旗问题)
    // 将数据分成三部分,然后再连接成链表,快排得partition思路是不稳定的,也就是说排序后不能保证原来的数据相对位置不变
    public static Node listPartition1(Node head, int pivot) {
        // 判空
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        // 计算链表size
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];

        // 写入Node数组
        i = 0;
        cur = head;
        // 等价写法 i < nodeArr.length
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);

        // 数组调整好之后,依次串起来即可
        // for循环,i从1开始,记住这种写法
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    // partition方法,给定的划分值pivot
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        // 因为是给定的pivot,大于区则是从nodeArr.length开始
        // 没有将数组中最右侧的元素划分在大于区里面
        int big = nodeArr.length;
        int index = 0;
        while (index < big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    // 交换节点数组
    public static void swap(Node[] nodeArr, int i, int j) {
        Node tmp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = tmp;
    }

    //-------------------------------------------------------

    // 方法二:不使用容器,只是调整指针
    // 建立三个小链表,small,equal,bigger
    // 然后选派节点到这三个链表中,最后将三个链表连接起来
    public static Node listPartition2(Node head, int pivot) {

        if (head == null) {
            return null;
        }

        // 定义需要变量
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;

        while (head != null) {
            // 先保存head后面一个节点
            next = head.next;
            // head和原来链表断开
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            // head指针移动到下一节点,再去进行判断
            head = next;
        }

        // 各个区域整体连接,整体的思路:
        // 小于区域的尾巴,连等于区域的头,等于区域的尾巴连大于区域的头
        // 各区域头尾只是指针,串联时不会影响数值的排序的

        // 1)如果有小于区域
        if (smallTail != null) {
            smallTail.next = equalHead;
            // 下一步,一定是需要用equalTail,去接大于区域的头,谁去连大于区域的头,谁就变成equalTail
            // 有等于区域,equalTail -> 等于区域的尾结点
            // 无等于区域,equalTail -> 小于区域的尾结点
            equalTail = (equalTail == null) ? smallTail : equalTail;
        }

        // 2)上个if不满足,则没有小于区域,则从等于区域开始判断
        // 因为小于区域和等于区域可能都没有,故equalTail还是需要进行判空
        // 即没有小于区域,但是有等于区域
        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        // 3)小于等于区域都没有的情况
        // KeyPoint 边界条件很多情况时,按顺序依次判断,这样不会漏掉情况,考虑更加完善!
        return (smallHead != null) ? smallHead : ((equalHead != null) ? equalHead : bigHead);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition1(head1, 5); // 2 1 5 5 8 9 7
        //head1 = listPartition2(head1, 5); // 1 2 5 5 7 9 8
        printLinkedList(head1);
    }
}
