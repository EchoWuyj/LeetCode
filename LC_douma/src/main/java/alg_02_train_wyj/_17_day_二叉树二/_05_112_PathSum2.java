package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 23:02
 * @Version 1.0
 */
public class _05_112_PathSum2 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        for (List<Integer> onePath : res) {
            int sum = 0;
            for (int val : onePath) {
                sum += val;
            }
            if (sum == target) return true;
        }
        return false;
    }

    private void dfs(TreeNode root, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }

        dfs(root.left, path, res);
        dfs(root.right, path, res);
        path.remove(path.size() - 1);
    }
}
