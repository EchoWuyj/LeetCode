package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 21:45
 * @Version 1.0
 */
public class _12_515_find_largest_value_in_each_tree_row {
     /*
        515. 在每个树行中找最大值
        您需要在二叉树的每一行中找到最大的值。

        输入:

              1
             / \
            3   2
           / \   \
          5   3   9

        输出: [1, 3, 9]

     */

    // KeyPoint 方法一 BFS
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            // 比较获取最大值，则初始化为最小值
            // 不能定义成 maxValue = 0，因为 0 不能算小，若是负数，则使用默认值 0，从而影响结果
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                maxValue = Math.max(maxValue, curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(maxValue);
        }
        return res;
    }

    // KeyPoint 方法二 DFS 前序遍历(递归)
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int level, List<Integer> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == level) {
            // level 第一节点，作为默认最大值
            res.add(node.val);
        } else {
            // 后续 level 层的其他节点，与之比较，设置最大值
            int maxValue = Math.max(res.get(level), node.val);
            // res 集合
            // index => 层数
            // value => 当前行最大值
            res.set(level, maxValue);
        }

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}
