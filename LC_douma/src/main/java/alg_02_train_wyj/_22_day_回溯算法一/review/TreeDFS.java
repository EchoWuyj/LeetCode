package alg_02_train_wyj._22_day_回溯算法一.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 19:50
 * @Version 1.0
 */
public class TreeDFS {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> proOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        res.add(root);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}
