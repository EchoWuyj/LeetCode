package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 20:02
 * @Version 1.0
 */
public class _01_112_PathSum2 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        for (int path : res) {
            if (path == target) {
                return true;
            }
        }
        return false;
    }

    public void dfs(TreeNode node, int pathSum, List<Integer> res) {
        if (node == null) return;

        int curPathSum = pathSum + node.val;
        if (node.left == null && node.right == null) {
            res.add(curPathSum);
        }

        dfs(node.left, curPathSum, res);
        dfs(node.right, curPathSum, res);
    }
}
