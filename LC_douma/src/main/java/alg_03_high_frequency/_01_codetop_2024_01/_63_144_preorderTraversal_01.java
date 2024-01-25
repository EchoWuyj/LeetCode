package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:38
 * @Version 1.0
 */
public class _63_144_preorderTraversal_01 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    public void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}
