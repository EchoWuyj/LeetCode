package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:50
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree1 {
     /*
        662. 二叉树最大宽度

         给定一个二叉树，编写一个函数来获取这个树的最大宽度。
         树的最大宽度是所有层中的最大宽度。
         这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
         每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

            输入:
               1
             /   \
            3     2
           / \     \
          5   3     9
            输出: 4

            输入:
              1
             /
            3
           / \
          5   3
            输出: 2

            输入:
              1
             / \
            3   2
           /
          5

            输出: 2

            输入:
              1
             / \
            3   2
           /     \
          5       9
         /         \
        6           7
            输出: 8


     */

    // 思路：
    // 先求出每层的宽度，比较再求出最大宽度即可
    // 每层宽度，关键求出该层 最左节点 到 最右节点 的长度
    // 给每个节点标记序号，从 1 开始，每层都是从左往右开始标记序号

    // 标记序号，定义成 node
    class Node {
        TreeNode node;
        // seqNo 父节点
        // 左子节点编号：2 * seqNo
        // 右子节点编号：2 * seqNo1+ 1
        int seqNum;

        Node(TreeNode node, int seqNo) {
            this.node = node;
            this.seqNum = seqNo;
        }
    }

    // KeyPoint 方法一 BFS
    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();

        // 编号从 1 开始
        queue.offer(new Node(root, 1));
        // maxWidth 最大宽度
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // for 循环遍历一层之后，start 和 end 都已经清零
            // 故将其定义在 for 循环外面是比较好的
            int start = 0, end = 0;
            // 一层一层遍历节点
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                TreeNode node = curr.node;
                int seqNum = curr.seqNum;
                // 最左
                if (i == 0) start = seqNum;
                // 最右
                if (i == size - 1) end = seqNum;
                if (node.left != null) {
                    queue.offer(new Node(node.left, 2 * seqNum));
                }
                if (node.right != null) {
                    queue.offer(new Node(node.right, 2 * seqNum + 1));
                }
            }
            // 每层宽度，通过比较，得最大宽度
            // start 和 end 两端都是包括在内的，故需要加 1
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }
}
