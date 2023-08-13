package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:03
 * @Version 1.0
 */
public class _05_116_populating_next_right_pointers_in_each_node {
     /* 
        116. 填充每个节点的下一个右侧节点指针
        给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点 => 满二叉树
    
        二叉树定义如下：
        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }

        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
        如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
        初始状态下，所有 next 指针都被设置为 NULL。

        输入：root = [1,2,3,4,5,6,7]
        输出：[1,#,2,3,#,4,5,6,7,#]
        解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点
              如图所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束

              1
            /   \
           2     3
          / \   / \
         4   5 6   7

              1 → null
            /   \
           2  →  3 → null
          / \    / \
         4 → 5 → 6 → 7 → null

        进阶：
        你只能使用常量级额外空间。
        使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     */

    // KeyPoint 方法一  BFS 层序遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                // 若当前节点不是这一层的最后一个节点，则设置 next
                if (i != size - 1) cur.next = queue.peek();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }

    // KeyPoint 方法二
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public Node connect3(Node root) {

        //    left：指向每层第一个节点
        //     cur：用于横向串联指针
        //      ↓
        //      1
        //    /   \
        //   2     3
        //  / \   / \
        // 4   5 6   7

        //               1 → null
        //   cur      /     \
        //  left →  2    →    3 → null
        //         / \       / \
        //       4  →  5  →  6 → 7 → null
        //         ↑       ↑
        //  cur.left.next  cur.right.next
        //    = cur.right;   = cur.next.left;
        //

        if (root == null) return null;
        Node left = root;
        Node cur;
        while (left.left != null) {
            // cur 跟着 left，每次循环都从每层第一个节点开始横向串联
            cur = left;
            while (cur != null) {
                // 调整两部分指针
                // 1. /_\ => 内部横向指针
                cur.left.next = cur.right;
                // 2. /_\ -> /_\ => 两者之间横向指针
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            // 满二叉树，下一层开始位置
            left = left.left;
        }
        return root;
    }

    // KeyPoint 方法三 DFS
    public Node connect(Node root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    // 前序遍历
    private void dfs(Node node) {

        //          1
        //       /     \
        //      2   →   3  => 不断窄方向深入，连接两侧节点
        //    /  \↘ ↙ / \
        //  4     5 →  6 → 7

        if (node == null) return;
        Node left = node.left;
        Node right = node.right;
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        dfs(node.left);
        dfs(node.right);
    }

    class Node {
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
}
