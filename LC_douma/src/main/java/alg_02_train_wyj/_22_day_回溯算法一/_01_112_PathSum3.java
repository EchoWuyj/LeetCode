package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 20:13
 * @Version 1.0
 */
public class _01_112_PathSum3 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, res);
        for (int num : res) {
            if (num == 0) return true;
        }
        return false;
    }

    public void dfs(TreeNode node, int parentNodeTarget, List<Integer> res) {
        if (node == null) return;
        int curNodeTarget = parentNodeTarget - node.val;
        if (node.left == null && node.right == null) {
            res.add(curNodeTarget);
        }
        dfs(node.left, curNodeTarget, res);
        dfs(node.right, curNodeTarget, res);
    }
}
