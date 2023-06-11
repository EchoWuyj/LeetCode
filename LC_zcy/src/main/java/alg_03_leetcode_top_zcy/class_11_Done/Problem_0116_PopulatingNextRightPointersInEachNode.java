package alg_03_leetcode_top_zcy.class_11_Done;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 13:38
 * @Version 1.0
 */

// 填充每个节点的下一个右侧节点指针
public class Problem_0116_PopulatingNextRightPointersInEachNode {
    
    /*
        给定一个完美二叉树,其所有叶子节点都在同一层,每个父节点都有两个子节点
        二叉树定义如下
            struct Node {
              int val;
              Node *left;
              Node *right;
              Node *next;
            }
        填充它的每个next指针,让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点,则将next指针设置为NULL
        初始状态下,所有next指针都被设置为NULL

                a → null
             b → c → null
           d→e → f→g → null

     */

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    // MyQueue和LinkedList区别
    // 1)LinkedList内部是将所有进来的对象包了一层Node<E>
    // 2)MyQueue使用的就是裸空间Node,只是存储两个引用(有限几个变量),没有使用辅助空间,节省空间

    // 自定义MyQueue结构,内部通过指针进行串联
    public static class MyQueue {
        public Node head;
        public Node tail;
        public int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 尾进
        public void offer(Node cur) {
            size++;
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }

        // 头出
        public Node poll() {
            size--;
            Node ans = head;
            // 头节点指向下个位置
            head = head.next;
            // MyQueue中每个节点在弹出之前将其next指针设置为null
            ans.next = null;
            return ans;
        }
    }

    // KeyPoint 方法一:仅仅使用有限几个变量,不使用额外容器(辅助空间)实现 -> 1ms
    //             -> 面试推荐,秀一下水平
    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // KeyPoint 通过MyQueue实现宽度优先遍历,没有使用LinkedList
        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 用来记录第一个弹出节点
            Node pre = null;
            int size = queue.size;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                // 再次while循环,修改pre的next指针(第一个弹出节点的next)
                // 指向第二个弹出节点,从而实现next指针串连
                if (pre != null) {
                    pre.next = cur;
                }
                // 记录第一个弹出节点
                pre = cur;
            }
        }
        return root;
    }

    //  KeyPoint 方法二:使用容器(双端队列) -> 100ms
    //       -> 笔试过程推荐,做完赶紧下一题
    public static Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        // 整体还是层次遍历
        LinkedList<Node> deque = new LinkedList<>();
        int size = 0;
        deque.add(root);
        while (!deque.isEmpty()) {
            ArrayList<Node> curLevel = new ArrayList<>();
            size = deque.size();
            for (int i = 0; i < size; i++) {
                Node cur = deque.pollLast();
                if (cur.left != null) {
                    deque.addFirst(cur.left);
                    // 将左孩子加入curLevel
                    curLevel.add(cur.left);
                }
                if (cur.right != null) {
                    deque.addFirst(cur.right);
                    // 将右孩子加入curLevel
                    curLevel.add(cur.right);
                }
            }
            // 遍历curLevel来设置执针
            int n = curLevel.size();
            for (int j = 0; j <= n - 2; j++) {
                curLevel.get(j).next = curLevel.get(j + 1);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // 自己进行测试
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        Node newRoot = connect1(root);
    }
}
