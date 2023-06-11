package alg_02_体系班_zcy.class09;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:17
 * @Version 1.0
 */
public class Code01_LinkedListMid {

    // 链表问题:关键边界问题处理
    // 面试时链表解题的方法论
    // 1)对于笔试,不用太在乎空间复杂度,一切为了时间复杂度
    //   有容器(哈希表,数组,队列,栈)尽量使用容器,不会在容器上卡你
    // 2)对于面试,时间复杂度依然放在第一位,但是一定要找到空间最省的方法
    //   最好不要使用容器,否则面试官认为你水平不行

    // 快慢指针
    // 1)输入链表头节点,奇数长度返回中点,偶数长度返回上中点
    // 2)输入链表头节点,奇数长度返回中点,偶数长度返回下中点
    // 3)输入链表头节点,奇数长度返回中点前一个,偶数长度返回上中点前一个
    // 4)输入链表头节点,奇数长度返回中点前一个,偶数长度返回下中点前一个

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    // 中点知识
    // 1)奇数中点
    //  1,2,3 中点:(3+1)/2=2
    //  1,2,3,4,5 中点:(5+1)/2=3
    //  => 1 中点:(1+1)/2=1
    // 2)偶数上下中点
    //  1,2,3,4 上中点就是2
    //  1,2,3,4 下中点就是3

    // 1)输入链表头节点,奇数长度返回中点,偶数长度返回上中点(左侧中点)
    public static Node midOrUpMidNode(Node head) {
        // 1)head为null,直接返回
        // 2)能执行head.next,说明第一个条件不成立,即head不为null,则是唯一的一个点,直接返回
        // 3)能执行head.next.next,说明前两个条件都不成立,则有两个节点,直接返回
        // 判断逻辑:依次判断1,2,3个条件成立,不成立的情况
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // 链表有3个点或以上
        // 只有链表足够长,才能使用快慢指针,即fast指针每次跳2个单位,得保证有2个单位
        Node slow = head.next;
        Node fast = head.next.next;
        // 依次模拟判断while的循环条件成立与否的情况
        // 一般只要将最开始临界的几个点梳理清楚,后面自然成立
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // for test
    // 通过数组容器实现,值得学习下,笔试推荐使用这种形式,提高AC的速度
    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        // ArrayList下表是从0开始的,size指的是数组大小,不是索引
        return arr.get((arr.size() - 1) / 2);
    }

    //--------------------------------------------------------------------------------------

    // 2)输入链表头节点,奇数长度返回中点,偶数长度返回下中点(右中点)
    public static Node midOrDownMidNode(Node head) {
        // 边界条件只能到1个节点,2个节点就不再是返回head
        if (head == null || head.next == null) {
            return head;
        }
        // 链表有2个点或以上
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // for test
    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    //--------------------------------------------------------------------------------------

    // 3)输入链表头节点,奇数长度返回中点的前一个,偶数长度返回上中点的前一个
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            // 链表个数太少,不存在中点,即直接返回null;
            return null;
        }
        // 需要一开始,slow,fast拉开足够大的距离
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // for test
    public static Node right3(Node head) {
        // 因为涉及中的前一个节点,存在节点不够的情况,故需要单独判断
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    //--------------------------------------------------------------------------------------

    // 4)输入链表头节点,奇数长度返回中点的前一个,偶数长度返回下中点的前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // for test
    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }

        //       O O O O
        // 索引  0 1 2 3
        // 个数  1 2 3 4
        // 奇数中点前一个索引:0
        // 偶数下中点前一个索引:1
        // 通过列方程求解
        // 奇数:(size-?)/2=0
        // 偶数:(size-?)/2=1
        // 解得:?=2
        return arr.get((arr.size() - 2) / 2);
    }

    //--------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无"); // 4
        System.out.println(ans2 != null ? ans2.value : "无"); // 4

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无"); // 4
        System.out.println(ans2 != null ? ans2.value : "无"); // 4

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无"); // 3
        System.out.println(ans2 != null ? ans2.value : "无"); // 3

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无"); // 3
        System.out.println(ans2 != null ? ans2.value : "无"); // 3
    }
}
