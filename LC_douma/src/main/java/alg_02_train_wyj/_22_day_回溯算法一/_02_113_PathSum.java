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
        dfs(root, target, path, res);
        return res;
    }

    public void dfs(TreeNode root, int target,
                    List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        path.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, target, path, res);
        dfs(root.right, target, path, res);
        path.remove(path.size() - 1);
    }


}
