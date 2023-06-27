package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */

public class _05_112_PathSum4 {

    // 思路：使用 target，反向相减，最后的结果是否有 0
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        // 使用 target，反向相减，最后的结果是否有 0
        dfs(root, target, res);
        System.out.println(res);
        for (int val : res) {
            if (val == 0) return true;
        }
        return false;
    }

    private void dfs(TreeNode node, int parentNodeTarget, List<Integer> res) { // 父亲节点的目标和
        if (node == null) return;
        int currNodeTarget = parentNodeTarget - node.val;
        if (node.left == null && node.right == null) {
            res.add(currNodeTarget);
        }
        dfs(node.left, currNodeTarget, res);
        dfs(node.right, currNodeTarget, res);
    }
}
