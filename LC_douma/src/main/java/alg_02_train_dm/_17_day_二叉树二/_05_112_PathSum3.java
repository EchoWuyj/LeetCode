package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:53
 * @Version 1.0
 */

public class _05_112_PathSum3 {

    // 思路：只是累加路径值，不用记录遍历过程中遇到每个节点
    public boolean hasPathSum(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();
        // root 路径和设置为 0
        dfs(root, 0, res);
        for (int onePathSum : res) {
            if (onePathSum == target) return true;
        }
        return false;
    }

    // 只是记录根节点到叶子节点路径累加和，而不用记录遍历过程中遇到的每个节点，节省空间
    private void dfs(TreeNode node, int pathSum, List<Integer> res) {// 父亲节点的路径和
        if (node == null) return;
        int curPathSum = pathSum + node.val;
        if (node.left == null && node.right == null) {
            // 加入的是 curPathSum，不是 pathSum，不要搞混淆了，经常容易搞混淆
            res.add(curPathSum);
        }
        dfs(node.left, curPathSum, res);
        dfs(node.right, curPathSum, res);
    }
}
