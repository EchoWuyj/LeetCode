package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 19:34
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree2 {
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

        // 这里 res 需要初始化为最大值，必须进行赋初值的操作
        // 否则，在 Math.min(res, currDepth) 存在问题
        int res = Integer.MAX_VALUE;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode curr = node.node;
            int depth = node.depth;

            // 只是对叶子节点进行判断，求解叶子节点深度的最小值
            if (curr.left == null && curr.right == null) {
                // return Math.min(res, depth);
                // => 不能直接返回，无法确定该叶子节点的深度为最小值
                // 区别 BFS，return levels;
                res = Math.min(res, depth);
            }
            if (curr.right != null) {
                // 将其封装成 Node 节点
                stack.push(new Node(curr.right, depth + 1));
            }
            if (curr.left != null) {
                stack.push(new Node(curr.left, depth + 1));
            }
        }

        return res;
    }
}
