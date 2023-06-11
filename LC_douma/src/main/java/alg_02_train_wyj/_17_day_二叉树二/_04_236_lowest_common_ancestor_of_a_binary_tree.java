package alg_02_train_wyj._17_day_二叉树二;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 23:30
 * @Version 1.0
 */
public class _04_236_lowest_common_ancestor_of_a_binary_tree {

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> parent = new HashMap<>();
        dfs(root, parent);

        Set<Integer> visited = new HashSet<>();
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parent.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode root, Map<Integer, TreeNode> parent) {
        if (root == null) return;
        if (root.left != null) parent.put(root.left.val, root);
        if (root.right != null) parent.put(root.right.val, root);
        dfs(root.left, parent);
        dfs(root.right, parent);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }
}
