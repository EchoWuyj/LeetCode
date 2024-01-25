package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _86_662_widthOfBinaryTree {

    class Node {
        TreeNode treeNode;
        int seqNum;

        public Node(TreeNode treeNode, int seqNum) {
            this.treeNode = treeNode;
            this.seqNum = seqNum;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int max = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 每层开始都将 start 和 end 重新置为 0
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
