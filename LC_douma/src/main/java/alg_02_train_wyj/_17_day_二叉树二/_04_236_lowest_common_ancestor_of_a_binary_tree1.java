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
public class _04_236_lowest_common_ancestor_of_a_binary_tree1 {

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Map<Integer, TreeNode> map = new HashMap<>();
        dfs(root, map);
        Set<Integer> set = new HashSet<>();

        while (p != null) {
            set.add(p.val);
            p = map.get(p.val);
        }

        while (q != null) {
            if (set.contains(q.val)) return q;
            q = map.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root, Map<Integer, TreeNode> map) {
        if (root == null) return;
        if (root.left != null) {
            map.put(root.left.val, root);
        }
        dfs(root.left, map);
        if (root.right != null) {
            map.put(root.right.val, root);
        }
        dfs(root.right, map);
    }
}
