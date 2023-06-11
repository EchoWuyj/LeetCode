package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 20:39
 * @Version 1.0
 */
public class _05_112_PathSum1 {
    class Node {
        TreeNode treeNode;
        int pathRemainSum;

        public Node(TreeNode treeNode, int pathRemainSum) {
            this.treeNode = treeNode;
            this.pathRemainSum = pathRemainSum;
        }
    }

    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, sum - root.val));
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            TreeNode treeNode = cur.treeNode;
            int pathRemainSum = cur.pathRemainSum;
            if (treeNode.left == null
                    && treeNode.right == null
                    && pathRemainSum == 0) {
                return true;
            }
            if (treeNode.right != null)
                stack.push(new Node(treeNode.right, pathRemainSum - treeNode.right.val));
            if (treeNode.left != null) {
                stack.push(new Node(treeNode.left, pathRemainSum - treeNode.left.val));
            }
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            return true;
        }
        boolean left = hasPathSum(root.left, sum - root.left.val);
        boolean right = hasPathSum(root.right, sum - root.right.val);
        return left || right;
    }

    public List<List<Integer>> allPath(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, res, path);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, res, path);
        dfs(root.right, res, path);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(2);
        node1.left = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode node6 = new TreeNode(13);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        node2.left = node6;
        node2.right = node7;
        node7.left = node8;
        node7.right = node9;

        System.out.println(new _05_112_PathSum1().allPath(root));
    }
}
