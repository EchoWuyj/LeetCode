package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 19:59
 * @Version 1.0
 */
public class _104_MaximumDepthOfBinaryTree {

    // KeyPoint 方法一：BFS
    // 二叉树的深度就是层数，每处理一层，maxDepth 就 + 1
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 将遍历处理的节点的左右子节点入队，等到后序的处理
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // 一层节点处理完之后，maxDepth++
            maxDepth++;
        }
        return maxDepth;
    }

    // 将 TreeNode 和 depth 两个信息封装到 Node 节点中
    // 从而保证二叉树上每个节点都有自己的深度
    private class Node {
        TreeNode node;
        int depth;

        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // KeyPoint 方法二：DFS(前序遍历)
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int maxDepth = 0;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        // 根节点深度是 1
        stack.push(new Node(root, 1));
        // KeyPoint 又是 while 和 if 不区分，经常将 while 循环写成 if 循环
        //      以后凡是涉及循环的地方，自己一定需要小心 => while 多次循环; if 单次循环
        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            // KeyPoint 替换原来前序遍历中访问操作
            // 当前节点
            TreeNode node = currNode.node;
            // 当前节点的深度
            int currDepth = currNode.depth;
            // 求最大深度
            maxDepth = Math.max(maxDepth, currDepth);
            if (node.right != null) {
                stack.push(new Node(node.right, currDepth + 1));
            }
            if (node.left != null) {
                stack.push(new Node(node.left, currDepth + 1));
            }
        }
        return maxDepth;
    }

    // KeyPoint 方法三：DFS(递归版本)
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = maxDepth1(root.left);
        int rightMaxDepth = maxDepth1(root.right);
        int maxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;
        return maxDepth;
    }
}
