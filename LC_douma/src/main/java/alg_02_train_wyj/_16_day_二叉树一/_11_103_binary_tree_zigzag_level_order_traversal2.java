package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-30 13:49
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal2 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode node, int level, List<List<Integer>> res) {
        if (node == null) return;

        if (res.size() == level) {
            LinkedList<Integer> levelList = new LinkedList<>();
            res.add(levelList);
        }

        LinkedList<Integer> levelList = (LinkedList<Integer>) res.get(level);

        if (level % 2 == 0) {
            levelList.add(node.val);
        } else {
            levelList.addFirst(node.val);
        }

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}
