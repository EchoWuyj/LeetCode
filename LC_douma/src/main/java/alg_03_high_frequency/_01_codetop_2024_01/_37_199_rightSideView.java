package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:04
 * @Version 1.0
 */
public class _37_199_rightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        // 递归过程，最先遍历最右侧元素
        if (res.size() == level) {
            res.add(root.val);
        }
        // 先右再左
        dfs(root.right, res, level + 1);
        dfs(root.left, res, level + 1);
    }
}
