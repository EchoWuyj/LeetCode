package alg_02_train_dm._16_day_二叉树一;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 23:25
 * @Version 1.0
 */
public class _13_199_binary_tree_right_side_view {
     /* 199. 二叉树的右视图
             给定一棵二叉树，想象自己站在它的右侧，
             按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

               1            <---
             /   \
            2     3         <---
             \
              5            <---
            输出：[1,3,5]

            KeyPoint 右视图 => 每层的最右边的节点 => BFS
     */

    // KeyPoint 方法一 BFS
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 判断 i 为每层最右节点索引，将其放到结果集中
                if (i == size - 1) res.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return res;
    }

    // KeyPoint 方法二 DFS 前序遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, 0, res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<Integer> res) {
        if (node == null) return;

        // 标准前序遍历，不断向最左遍历的过程中，同时获取每层的第一节点，得到的是左视图
        // 调整递归的顺序，即可得到右视图

        // 每层的第一节点
        if (res.size() == currLevel) {
            res.add(node.val);
        }

        // 从右边开始遍历
        preorder(node.right, currLevel + 1, res);
        preorder(node.left, currLevel + 1, res);
    }
}
