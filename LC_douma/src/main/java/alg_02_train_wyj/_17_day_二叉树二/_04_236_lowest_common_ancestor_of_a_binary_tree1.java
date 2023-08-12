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
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        dfs(root, parentMap);
        Set<Integer> visited = new HashSet<>();
        while (p != null) {
            visited.add(p.val);
            p = parentMap.get(p.val);
        }

        while (q != null) {
            if (visited.contains(p.val)) return q;
            q = parentMap.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root, Map<Integer, TreeNode> parentMap) {
        if (root == null) return;

        if (root.left != null) parentMap.put(root.left.val, root);
        dfs(root.left, parentMap);

        if (root.right != null) parentMap.put(root.right.val, root);
        dfs(root.right, parentMap);
    }
}
