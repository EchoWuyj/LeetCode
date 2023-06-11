package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:12
 * @Version 1.0
 */
public class _05_112_PathSum3 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        for (int val : res) {
            if (val == target) return true;
        }
        return false;
    }

    private void dfs(TreeNode root, int pathSum, List<Integer> res) {
        if (root == null) return;
        pathSum += root.val;
        if (root.left == null && root.right == null) {
            res.add(pathSum);
        }
        dfs(root.left, pathSum, res);
        dfs(root.right, pathSum, res);
    }
}
