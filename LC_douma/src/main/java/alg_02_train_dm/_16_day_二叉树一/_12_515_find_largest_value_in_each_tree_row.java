package alg_02_train_dm._16_day_二叉树一;

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
     /* 515. 在每个树行中找最大值
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
        preorder(root, 0, res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<Integer> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            // currLevel 的第一节点，作为默认最大值
            res.add(node.val);
        } else {
            // 后续 currLevel 层的其他节点，与之比较，设置最大值
            int maxValue = Math.max(res.get(currLevel), node.val);
            res.set(currLevel, maxValue);
        }

        preorder(node.left, currLevel + 1, res);
        preorder(node.right, currLevel + 1, res);
    }
}
