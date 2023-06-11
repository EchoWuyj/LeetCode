package alg_02_体系班_zcy.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:08
 * @Version 1.0
 */
public class Code01_LevelTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 层次遍历(图的宽度优先遍历)(队列实现)
    // 1)先将root节点加入队列中
    // 2)队列中弹出一个节点cur,并打印
    // 3)cur有左入左,有右入右,先左再右
    // 4)周而复始,直到队列为空
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        // Queue是接口名,常规使用LinkedList实现,而LinkedList底层是双端队列
        Queue<Node> queue = new LinkedList<>();
        //offer方法:将指定的元素添加到列表的尾部(最后一个元素)
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }
}
