package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */

// KeyPoint 使用 target，反向相减，最后的结果是否有 0
public class _05_112_PathSum4 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        // 使用 target，反向相减，最后的结果是否有 0
        dfs(root, target, res);
        System.out.println(res);
        for (int val : res) {
            if (val == 0) return true;
        }
        return false;
    }

    private void dfs(TreeNode node, int parentNodeTarget, List<Integer> res) { // 父亲节点的目标和
        if (node == null) return;
        int currNodeTarget = parentNodeTarget - node.val;
        if (node.left == null && node.right == null) {
            res.add(currNodeTarget);
        }
        dfs(node.left, currNodeTarget, res);
        dfs(node.right, currNodeTarget, res);
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

        System.out.println(new _05_112_PathSum4().hasPathSum(root, 13));
    }
}
