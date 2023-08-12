package alg_02_train_wyj._18_二叉树三;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 9:35
 * @Version 1.0
 */
public class _09_98_validate_binary_search_tree {
    public boolean isValidBST1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        int size = res.size();
        for (int i = 1; i < size; i++) {
            if (res.get(i) <= res.get(i - 1)) return false;
        }
        return true;
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        dfs(node.left, res);
        res.add(node.val);
        dfs(node.right, res);
    }

    private boolean isBST = true;
    private TreeNode prev = null;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        dfs1(root);
        return isBST;
    }

    private void dfs1(TreeNode node) {
        if (node == null) return;
        dfs1(node.left);
        if (prev != null && node.val <= prev.val) {
            isBST = false;
            return;
        }
        prev = node;
        dfs1(node.right);
    }

    public boolean isValidBST3(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        boolean left = dfs(node.left, min, node.val);
        boolean right = dfs(node.right, node.val, max);
        return left && right;
    }
}
