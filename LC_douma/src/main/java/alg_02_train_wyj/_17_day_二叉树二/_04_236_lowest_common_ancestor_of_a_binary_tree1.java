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
        Map<Integer, TreeNode> map = new HashMap<>();
        dfs(root, map);
        Set<Integer> visited = new HashSet<>();

        while (p != null) {
            visited.add(p.val);
            p = map.get(p.val);
        }

        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = map.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode node, Map<Integer, TreeNode> map) {
        if (node == null) return;

        if (node.left != null) map.put(node.left.val, node);
        dfs(node.left, map);

        if (node.right != null) map.put(node.right.val, node);
        dfs(node.right, map);
    }
}
