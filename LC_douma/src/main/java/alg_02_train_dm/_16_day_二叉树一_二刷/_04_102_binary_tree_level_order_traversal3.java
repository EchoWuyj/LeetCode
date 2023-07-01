package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 15:31
 * @Version 1.0
 */
public class _04_102_binary_tree_level_order_traversal3 {

    // KeyPoint 方法三 层序遍历 => 前序遍历-递归 实现
    //                         => 需要掌握，后续多次使用，打败 100 %
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    // 每次调用 preorder 都是访问一个节点
    // 节点和所属层的关系，通过方法参数控制
    private void dfs(TreeNode node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;
        // 在递的过程，添加额外的处理逻辑：
        // 处理当前遍历的节点，将节点放到所属的结果集中
        if (res.size() == currLevel) {
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add(node.val);
            res.add(levelNodes);
        } else {
            res.get(currLevel).add(node.val);
        }

        dfs(node.left, currLevel + 1, res);
        dfs(node.right, currLevel + 1, res);
    }
}
