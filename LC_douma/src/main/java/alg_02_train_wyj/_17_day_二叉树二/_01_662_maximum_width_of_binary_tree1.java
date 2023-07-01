package alg_02_train_wyj._17_day_二叉树二;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 23:16
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree1 {

    class Node {
        TreeNode node;
        int seqNum;

        Node(TreeNode node, int seqNum) {
            this.node = node;
            this.seqNum = seqNum;
        }
    }

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));

        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                TreeNode node = cur.node;
                int seqNum = cur.seqNum;
                if (i == 0) start = seqNum;
                if (i == size - 1) end = seqNum;

                if (node.left != null) queue.offer(new Node(node.left, 2 * seqNum));
                if (node.right != null) queue.offer(new Node(node.right, 2 * seqNum + 1));
            }
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }
}
