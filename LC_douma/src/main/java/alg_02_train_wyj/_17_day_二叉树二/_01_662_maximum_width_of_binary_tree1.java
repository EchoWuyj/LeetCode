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
        TreeNode treeNode;
        int seqNum;

        public Node(TreeNode treeNode, int seqNum) {
            this.treeNode = treeNode;
            this.seqNum = seqNum;
        }
    }

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int max = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i == 0) start = cur.seqNum;
                if (i == size - 1) end = cur.seqNum;
                TreeNode treeNode = cur.treeNode;
                int seqNum = cur.seqNum;
                if (treeNode.left != null) queue.offer(new Node(treeNode.left, 2 * seqNum));
                if (treeNode.right != null) queue.offer(new Node(treeNode.right, 2 * seqNum + 1));
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
