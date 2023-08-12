package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:51
 * @Version 1.0
 */
public class _06_113_PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        dfs(root, target, path, res);
        return res;
    }

    public void dfs(TreeNode root, int remainPath, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        path.add(root.val);
        remainPath -= root.val;
        if (root.left == null && root.right == null && remainPath == 0) {
            res.add(new ArrayList<>(path));
        }

        dfs(root.left, remainPath, path, res);
        dfs(root.right, remainPath, path, res);
        path.remove(path.size() - 1);
    }
}
