package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-24 19:40
 * @Version 1.0
 */
public class _01_112_PathSum1 {
    public boolean hasPathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        for (List<Integer> list : res) {
            int sum = 0;
            for (int num : list) {
                sum += num;
            }
            if (sum == target) {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
            return;
        }
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        path.remove(path.size() - 1);
    }
}
