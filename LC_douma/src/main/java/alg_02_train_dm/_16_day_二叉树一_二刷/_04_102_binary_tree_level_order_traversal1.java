package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 12:34
 * @Version 1.0
 */

// 详细注释 BinaryTree_04_LevelOrder
public class _04_102_binary_tree_level_order_traversal1 {
      /*
         102. 二叉树的层序遍历
              给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
             （即逐层地，从左到右访问所有节点）
     */

    // KeyPoint 方法一 层序遍历(迭达)
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelNodes.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelNodes);
        }

        return res;
    }
}
