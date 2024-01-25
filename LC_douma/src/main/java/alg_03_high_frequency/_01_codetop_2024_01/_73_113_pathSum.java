package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _73_113_pathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, target, path, res);
        return res;
    }

    public void dfs(TreeNode root, int target,
                    List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;

        // 不能附加该条件，因为 target 值有可能为负值
        // if (target < 0) return;

        // 从根节点到叶子节点都得记录，不管怎么样先进行 add 操作
        path.add(root.val);
        target -= root.val;
        // 除了判断叶子节点，还需要满足 target 为 0 的条件
        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        }

        dfs(root.left, target, path, res);
        dfs(root.right, target, path, res);

        // 在完成当前节点的所有递归探索(包括其左右子树)后，移除当前节点
        // 该操作与左右子树本身无关
        path.remove(path.size() - 1);
    }
}
