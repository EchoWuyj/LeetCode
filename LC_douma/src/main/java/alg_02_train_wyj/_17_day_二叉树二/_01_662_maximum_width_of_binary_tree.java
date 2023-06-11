package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 23:16
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree {
    class Node {
        TreeNode treeNode;
        int seq;

        public Node(TreeNode treeNode, int seq) {
            this.treeNode = treeNode;
            this.seq = seq;
        }
    }

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int startSeq = 0, endSeq = 0;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                TreeNode treeNode = node.treeNode;
                int seq = node.seq;
                if (i == 0) startSeq = seq;
                if (i == size - 1) endSeq = seq;
                if (treeNode.left != null) queue.offer(new Node(treeNode.left, 2 * seq));
                if (treeNode.right != null) queue.offer(new Node(treeNode.right, 2 * seq + 1));
            }
            maxWidth = Math.max(maxWidth, endSeq - startSeq + 1);
        }
        return maxWidth;
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        return dfs(root, 0, 1, start, end);
    }

    private int dfs(TreeNode root, int level, int seq, List<Integer> start, List<Integer> end) {
        if (root == null) return 0;
        if (start.size() == level) {
            start.add(seq);
            end.add(seq);
        } else {
            end.set(level, seq);
        }

        int leftMaxWidth = dfs(root.left, level + 1, 2 * seq, start, end);
        int rightMaxWidth = dfs(root.right, level + 1, 2 * seq + 1, start, end);
        int width = end.get(level) - start.get(level) + 1;
        return Math.max(width, Math.max(leftMaxWidth, rightMaxWidth));
    }
}
