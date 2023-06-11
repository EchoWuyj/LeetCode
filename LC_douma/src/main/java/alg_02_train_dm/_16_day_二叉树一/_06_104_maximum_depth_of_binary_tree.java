package alg_02_train_dm._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 15:30
 * @Version 1.0
 */

// KeyPoint 详细注释 _104_MaximumDepthOfBinaryTree
public class _06_104_maximum_depth_of_binary_tree {

      /* 104. 二叉树的最大深度
       给定一个二叉树，找出其最大深度。
       二叉树的深度为:根节点到最远叶子节点的最长路径上的节点数。
       说明: 叶子节点是指没有子节点的节点。

       输入：
            3
           / \
          9  20
            /  \
           15   7
       输出：3
       KeyPoint 叶子节点的 Depth 数值上等价于从根节点到叶子节点沿途的节点个数
     */

    // KeyPoint 方法一 BFS
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 注意定义变量 levels 位置 ，放在 while 循环里面，while 结束 levels 就释放了
        // 所以需要将 levels 定义在 while 的外面
        int levels = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            levels++;
        }
        return levels;
    }

    // 额外信息 depth 定义在 Node 节点中
    private class Node {
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
            Node node = stack.pop();
            TreeNode curr = node.node;
            int currDepth = node.depth;

            // 只要叶子节点进行比较 Depth 即可
            if (curr.left == null && curr.right == null) {
                maxDepth = Math.max(maxDepth, currDepth);
            }

            if (curr.right != null) {
                stack.push(new Node(curr.right, currDepth + 1));
            }

            if (curr.left != null) {
                stack.push(new Node(curr.left, currDepth + 1));
            }
        }
        return maxDepth;
    }

    // KeyPoint 方法三 DFS 递归 - 前序遍历 => 从上往下计算
    // 定义全局变量，保证能被每个方法访问
    private int maxDepth = 0;

    public int maxDepth3(TreeNode root) {
        preorder(root, 1);
        return maxDepth;
    }

    // 每次调用 preorder 都是访问一个节点
    // KeyPoint 递归没有返回值，通过全局变量 res 获取
    private void preorder(TreeNode node, int depth) {
        if (node == null) return;
        // 处理当前的节点
        if (node.left == null && node.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }
        preorder(node.left, depth + 1);
        preorder(node.right, depth + 1);
    }

    // KeyPoint 方法四 DFS 递归 - 后序遍历 => 从下往上计算 => 推荐使用
    // 递归函数有返回值
    public int maxDepth(TreeNode root) {

        // 核心:从左，右子树获取信息，返回根节点，在根节点处进行信息加工
        //      递归遍历每颗子树，最终结果需要返回，以该 root 为根节点的最大深度

        if (root == null) return 0;
        // 左子树信息
        int leftMaxDepth = maxDepth(root.left);
        // 右子树信息
        int rightMaxDepth = maxDepth(root.right);
        // 信息加工后返回
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
