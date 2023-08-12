package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */

public class _05_112_PathSum4 {

    // 思路：使用 target，反向相减，最后的结果是否有 0
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, res);
        for (int remainPath : res) {
            if (remainPath == 0) return true;
        }
        return false;
    }

    private void dfs(TreeNode node, int remainPath, List<Integer> res) {
        if (node == null) return;
        remainPath -= node.val;
        if (node.left == null && node.right == null) {
            res.add(remainPath);
        }
        dfs(node.left, remainPath, res);
        dfs(node.right, remainPath, res);
    }
}
