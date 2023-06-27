package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 20:18
 * @Version 1.0
 */
public class _02_113_PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res, target);
        return res;
    }

    public static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res, int target) {
        if (node == null) return;
        path.add(node.val);
        int curTarget = target - node.val;
        if (node.left == null && node.right == null && curTarget == 0) {
            res.add(new ArrayList<>(path));
        }
        dfs(node.left, path, res, curTarget);
        dfs(node.right, path, res, curTarget);
        path.remove(path.size() - 1);
    }
}
