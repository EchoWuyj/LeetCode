package alg_02_train_wyj._18_二叉树三;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 9:35
 * @Version 1.0
 */
public class _09_98_validate_binary_search_tree1 {
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        ArrayList<Integer> res = new ArrayList<>();
        int n = res.size();
        dfs(root, res);
        for (int i = 1; i < n; i++) {
            if (res.get(i) <= res.get(i - 1)) return false;
        }
        return true;
    }

    public void dfs(TreeNode node, ArrayList<Integer> res) {
        if (node == null) return;
        dfs(node.left, res);
        res.add(node.val);
        dfs(node.right, res);
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
