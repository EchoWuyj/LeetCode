package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */

// KeyPoint 只是累加路径值，不用记录遍历过程中遇到每个节点
public class _05_112_PathSum3 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        // root 父亲节点的路径和设置为 0
        dfs(root, 0, res);
        System.out.println(res); // [27, 22, 26, 22, 18]
        for (int onePathSum : res) {
            if (onePathSum == target) return true;
        }
        return false;
    }

    // 只是记录根节点到叶子节点路径累加和，而不用记录遍历过程中遇到的每个节点，节省空间
    private void dfs(TreeNode node, int parentNodePathSum, List<Integer> res) {// 父亲节点的路径和
        if (node == null) return;
        int currNodePathSum = parentNodePathSum + node.val;
        if (node.left == null && node.right == null) {
            res.add(currNodePathSum);
        }
        dfs(node.left, currNodePathSum, res);
        dfs(node.right, currNodePathSum, res);
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

        System.out.println(new _05_112_PathSum3().hasPathSum(root, 22));
    }
}
