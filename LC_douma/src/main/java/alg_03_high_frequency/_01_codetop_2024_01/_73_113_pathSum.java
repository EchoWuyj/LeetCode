package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _73_113_pathSum {

    // 路径总和 II => 二叉树背景
    // 深度优先遍历
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, target, path, res);
        return res;
    }

    public void dfs(TreeNode root, int target,
                    List<Integer> path, List<List<Integer>> res) {

        // 节点为空作为边界条件
        if (root == null) return;

        // 不能将其作为边界条件，因为题目中 target 值有可能为负值
        // if (target < 0) return;

        // 从根节点到叶子节点都得记录，不管怎么样先进行 add 操作
        // 类似于：子集
        path.add(root.val);
        // path 添加 root.val 之后，再去将 root.val 从 target 中进行排除
        target -= root.val;

        // 除了判断叶子节点，还需要满足 target 为 0 的条件
        // 整体都满足才能加入 res
        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        }

        // 二叉树为背景，则不是 for 循环，而是常规左右子树遍历
        dfs(root.left, target, path, res);
        dfs(root.right, target, path, res);

        // 在完成当前节点的所有递归探索(包括其左右子树)后，移除当前节点
        // 该操作与左右子树本身无关
        path.remove(path.size() - 1);
    }
}
