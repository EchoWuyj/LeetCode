package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 15:15
 * @Version 1.0
 */
public class _05_107_binary_tree_level_order_traversal_ii {

     /* 107. 二叉树的层序遍历 II
        给定一个二叉树，返回其节点值 自底向上 的层序遍历。
        （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

        输入：
            3
           / \
          9  20
            /  \
           15   7
        输出：
        [
            [15, 7],
            [9, 20],
            [3]
        ]
     */

    // KeyPoint 方法一 BFS
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        // LinkedList<List<Integer>> res = new LinkedList<>();
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
        // 反转
        Collections.reverse(res);
        // 推荐 res 替换成 LinkedList，每次 addFirst，即 res.addFirst(levelList);
        // 实现逆序
        // 1.最晚出现元素 在 First
        // 2.最早出现元素 在 Last
        // => 一定是 addFirst，而不是 addLast，因为 add 默认就是 addLast
        return res;
    }

    // KeyPoint 方法二 前序遍历(递归) 实现 层序遍历
    //                 => 相对简洁，推荐使用
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        preorder(root, 0, res);
        // 反转
        Collections.reverse(res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            List<Integer> levelList = new ArrayList<>();
            levelList.add(node.val);
            res.add(levelList);
        } else {
            res.get(currLevel).add(node.val);
        }

        preorder(node.left, currLevel + 1, res);
        preorder(node.right, currLevel + 1, res);
    }
}
