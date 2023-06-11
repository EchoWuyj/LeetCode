package alg_02_train_dm._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 19:21
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree {
     /* 111. 二叉树的最小深度
        给定一个二叉树，找出其最小深度。
        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
        说明：叶子节点是指没有子节点的节点。

        输入：
              1
             / \
            2   3
           / \
          4   5

         输出：2

        输入：
              1
             / \
            2  NULL -> 这不是叶子节点，而是空节点
           /        -> 叶子节点的左右子树都为 null    3
          4                                       / \
         /                                     null null
        5
       /
      6
         输出： 5
     */

    // KeyPoint 遇到几乎所有的二叉树问题都想到将其转成二叉树遍历的问题 => 两个类 DFS 和 BFS

    // KeyPoint 方法一 BFS
    // BFS 可以计算二叉树最大深度，有几层则深度为几，类比求解最小深度
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levels = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 将 levels++ 上移，避免 root 为叶子节点，返回 0
            levels++;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 层次遍历中，遇到第一个叶子节点，直接返回 levels 即可
                if (curr.left == null && curr.right == null) {
                    return levels;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return levels;
    }

    private class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // KeyPoint 方法二 DFS 迭代 - 前序遍历
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;

        // bug 修复：这里需要初始化为最大值
        // KeyPoint 必须进行赋初值的操作，否则在 Math.min(res, currDepth) 存在问题
        int res = Integer.MAX_VALUE;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode curr = node.node;
            int currDepth = node.depth;

            // 只是对叶子节点进行判断，求解叶子节点深度的最小值
            if (curr.left == null && curr.right == null) {
                res = Math.min(res, currDepth);
            }

            if (curr.right != null) {
                stack.push(new Node(curr.right, currDepth + 1));
            }

            if (curr.left != null) {
                stack.push(new Node(curr.left, currDepth + 1));
            }
        }

        return res;
    }

    // KeyPoint 方法三 DFS 递归 - 前序遍历
    private int res = Integer.MAX_VALUE;

    public int minDepth3(TreeNode root) {
        // bug 修复：需要加上特判，要不然会返回最大值
        if (root == null) return 0;
        preorder(root, 1);
        return res;
    }

    private void preorder(TreeNode node, int depth) {
        if (node == null) return;
        // 遍历到叶子节点进行判断
        if (node.left == null && node.right == null) {
            res = Math.min(res, depth);
        }

        preorder(node.left, depth + 1);
        preorder(node.right, depth + 1);
    }

    // KeyPoint 方法四 DFS 递归 - 后序遍历
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        // 只有是叶子节点，才将其深度设置为 1
        // 即只是从叶子节点开始，向上累加最短深度
        if (root.left == null && root.right == null) return 1;

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        // 左子树为 null，不是叶子节点，没有最小深度的说法，直接使用另外一侧数的最小深度
        // 即直接返回右子树最小深度 + 1 ，作为该树的最小深度
        if (root.left == null) {
            return rightMinDepth + 1;
        } else if (root.right == null) {
            return leftMinDepth + 1;
        } else {
            return Math.min(leftMinDepth, rightMinDepth) + 1;
        }

        // KeyPoint 递归逻辑只是求当前层子树的最小深度，并没有求整个树最小深度
//        if (root == null) return 0;
//        int leftMinDepth = minDepth(root.left);
//        int rightMinDepth = minDepth(root.right);
//        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
}
