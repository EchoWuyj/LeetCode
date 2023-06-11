package alg_02_体系班_wyj.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-10-07 14:36
 * @Version 1.0
 */
public class Code05_TreeMaxWidth02 {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxWidth = 1;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> indexList = new LinkedList<>();
        queue.add(root);
        indexList.add(1);
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            int index = indexList.removeFirst();
            if (node.left != null) {
                queue.add(node.left);
                indexList.add(2 * index);
            }

            if (node.right != null) {
                queue.add(node.right);
                indexList.add(2 * index + 1);
            }

            if (size == 0) {
                if (indexList.size() >= 2) {
                    maxWidth = Math.max(maxWidth, (indexList.getLast() - indexList.getFirst() + 1));
                }
                size = queue.size();
            }
        }
        return maxWidth;
    }
}
