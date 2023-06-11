package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:30
 * @Version 1.0
 */
public class _05_112_PathSum4 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, res);
        for (int val : res) {
            if (val == 0) return true;
        }
        return false;
    }

    private void dfs(TreeNode root, int pathRemain, List<Integer> res) {
        if (root == null) return;
        pathRemain -= root.val;
        if (root.left == null && root.right == null) {
            res.add(pathRemain);
        }
        dfs(root.left, pathRemain, res);
        dfs(root.right, pathRemain, res);
    }
}
