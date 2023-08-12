package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:52
 * @Version 1.0
 */

public class _05_112_PathSum2 {

    // KeyPoint 正式求解路径总和问题
    // 思路
    // 1.先获取所有路径
    // 2.再计算所有路径的路径和，是否有等于目标和的路径

    // 修改返回值类型为 boolean
    public boolean hasPathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 先获取所有路径
        dfs(root, path, res);
        // 计算所有路径的路径和，是否有等于目标和的路径
        // KeyPoint 注意 List 需要加上泛型 <Integer>
        for (List<Integer> onePath : res) {
            int sum = 0;
            // 循环累加一条路径中每个路径值
            for (int val : onePath) sum += val;
            if (sum == target) return true;
        }
        return false;
    }

    private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
            // return;

            // KeyPoint 关于 return 语句
            // 1.叶子节点后不能加上 return 语句，必须是遇到 null，才能通过 return 返回上层
            // 2.若是遇到叶子节点，将其加入 path 后，就 return 结束 dfs，返回上层
            //   则没法执行还原现场操作，即 path.remove，导致代码有 bug

            //             1
            //           /  \
            //  node →  2    3
            //         /\   / \
            //        4  5  6  7
            //        ↑
            //    node.left

            // 执行 dfs(node.left,path,res)，递归下一层到 node(4)
            // 其中 node(4) 是叶子节点，path[1,2,4]，只有遍历其左右空子树后
            // 还原现场，将 node(4) 从 path中移除，path[1,2]
            // 再去执行 dfs(node.right,path,res) 递归下一层到 node(5)
        }
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        // 记得回溯，还原现场
        path.remove(path.size() - 1);
    }
}
