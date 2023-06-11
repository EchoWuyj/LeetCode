package alg_01_ds_wyj._02_tree._01_btree.train;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 17:25
 * @Version 1.0
 */
public class _112_PathSum {
    class Node {
        TreeNode node;
        int pathRemainSum;

        public Node(TreeNode node, int pathRemainSum) {
            this.node = node;
            this.pathRemainSum = pathRemainSum;
        }
    }

    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, targetSum - root.val));
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            TreeNode curTreeNode = cur.node;
            int currPathRemainSum = cur.pathRemainSum;

            if (curTreeNode.left == null && curTreeNode.right == null && currPathRemainSum == 0)
                return true;
            if (curTreeNode.right != null)
                stack.push(new Node(curTreeNode.right, currPathRemainSum - curTreeNode.right.val));
            if (curTreeNode.left != null)
                stack.push(new Node(curTreeNode.left, currPathRemainSum - curTreeNode.left.val));
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum = sum - root.val;
        if (root.left == null && root.right == null && sum == 0) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
