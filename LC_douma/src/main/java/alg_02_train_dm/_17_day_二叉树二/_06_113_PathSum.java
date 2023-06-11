package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:54
 * @Version 1.0
 */
public class _06_113_PathSum {
    /* 113. 路径总和 II

        给你二叉树的根节点 root 和一个整数目标和 targetSum
        找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

        叶子节点 是指没有子节点的节点。

     */

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, target, path, res);
        return res;
    }

    private void dfs(TreeNode node, int targetSum,
                     List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        int currNodeTarget = targetSum - node.val;
        // node 是叶子节点 && currNodeTarget == 0 => 找到一条符合要求的路径，加入 res
        // 提高性能，在遍历的过程中就已经找到了符合条件的路径，将其加入到 res 中
        if (node.left == null && node.right == null && currNodeTarget == 0) {
            res.add(new ArrayList<>(path));
        }
        dfs(node.left, currNodeTarget, path, res);
        dfs(node.right, currNodeTarget, path, res);
        // 回溯的过程中，将当前的节点从 path 中删除，不要忘记了
        path.remove(path.size() - 1);
    }
}
