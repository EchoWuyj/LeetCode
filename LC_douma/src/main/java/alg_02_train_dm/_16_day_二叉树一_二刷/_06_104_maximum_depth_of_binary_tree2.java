package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 16:26
 * @Version 1.0
 */
public class _06_104_maximum_depth_of_binary_tree2 {

    // 额外信息 depth 定义在 Node 节点中
    class Node {
        TreeNode node;
        int depth;

        Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // KeyPoint 方法二 DFS 迭代 - 前序遍历
    // 思路:前序遍历中计算每个叶子节点深度，找到最大的深度
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        // KeyPoint 比较之前最好先赋值，即需要初始化
        int maxDepth = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            TreeNode node = cur.node;
            int depth = cur.depth;

            // maxDepth 和 叶子节点 depth 比较
            if (node.left == null && node.right == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
            // 先序遍历
            if (node.right != null) {
                stack.push(new Node(node.right, depth + 1));
            }
            if (node.left != null) {
                stack.push(new Node(node.left, depth + 1));
            }
        }
        return maxDepth;
    }
}
