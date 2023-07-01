package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 15:55
 * @Version 1.0
 */
public class _04_102_binary_tree_level_order_traversal3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if (node == null) return;

        if (res.size() == level) {
            List<Integer> levelList = new ArrayList<>();
            levelList.add(node.val);
            res.add(levelList);
        } else {
            res.get(level).add(node.val);
        }

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}
