package alg_03_high_frequency._01_codetop.top_100;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:04
 * @Version 1.0
 */
public class _37_199_rightSideView {

    // 二叉树的右视图
    // 深度优先遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        // 通过 res.size() 和 level 限制，将这种情况下 root.val 加入 res
        if (res.size() == level) {
            res.add(root.val);
        }
        // 递归过程先遍历最右侧元素
        // 先右再左
        dfs(root.right, res, level + 1);
        dfs(root.left, res, level + 1);
    }
}
