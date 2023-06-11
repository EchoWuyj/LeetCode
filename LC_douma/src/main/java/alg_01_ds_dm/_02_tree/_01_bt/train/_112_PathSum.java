package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 20:48
 * @Version 1.0
 */

// 112. 路径总和
public class _112_PathSum {
    /*
        给你二叉树的根节点  root 和一个表示目标和的整数  targetSum 。
        判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和  targetSum 。
        如果存在，返回 true ；否则，返回 false 。
        叶子节点 是指没有子节点的节点。
     */

    private class Node {
        TreeNode node; // 当前树节点
        int pathRemainSum; // 从根节点到当前节点的路径剩余和

        public Node(TreeNode node, int pathRemainSum) {
            this.node = node;
            this.pathRemainSum = pathRemainSum;
        }
    }

    // KeyPoint 方法一：DFS(前序遍历) => 定义节点 Node，Node 记录额外信息
    //          本质：在 DFS (前序遍历) 模板的基础上修改而来的
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<Node> stack = new Stack<>();
        // 最初 root 的 val 值不要忘记减去
        stack.push(new Node(root, sum - root.val));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // 分别获取 node 节点的两个属性
            TreeNode currNode = node.node;
            int currPathRemainSum = node.pathRemainSum;

            // 如果当前节点为叶子节点，且当前节点的路径和等于目标和，则直接返回 true
            // KeyPoint 一定得保证是叶子节点，即左右子节点都是 null，则该节点是叶子节点

            if (currNode.left == null && currNode.right == null && currPathRemainSum == 0) {
                return true;
            }
            // 遍历过程 pathRemainSum 都是在变化的，不能一直使用最开始的 sum，切记！
            // 同时，pathRemainSum 减去的是 right 或者 left 的 val，而不是 currNode.val
            if (currNode.right != null) {
                // 使用 currNode 代替 node.node，简化代码
                stack.push(new Node(currNode.right, currPathRemainSum - currNode.right.val));
            }
            if (currNode.left != null) {
                stack.push(new Node(currNode.left, currPathRemainSum - currNode.left.val));
            }
        }
        // 遍历完所有节点都不存在，则返回 false
        return false;
    }

    // KeyPoint 方法二：DFS 递归
    // DFS 前中后序都可以递归实现
    public boolean hasPathSum(TreeNode root, int sum) {
        // 叶子节点的左右节点，返回上层的条件
        if (root == null) return false;

        sum = sum - root.val;
        // 如果当前节点是叶子，检查 sum 值是否为 0，也就是是否找到了给定的目标和。
        if (root.left == null && root.right == null) {
            return sum == 0;
        }

        // 如果当前节点不是叶子，对它的所有孩子节点，递归调用 hasPathSum 函数
        // 这里 sum 不用再减去 root.left.val 或者 root.right.val，因为在递的过程已经减过了 sum = sum - root.val;

        // 同时，也不能这样写，root.left.val，其中 root.left 存在空指针异常，此时无法获取 val，
        // 递归边界判空，只是只是针对形参的第一个位置 root.left 和 root.right，形参的后一个位置无法保证
        boolean hasPathLeft = hasPathSum(root.left, sum);
        boolean hasPathRight = hasPathSum(root.right, sum);

        // 只要左子树或者右子树有就返回 true 即可
        // 边界条件中，root == null，返回 false，最终的结果不是一定就为 false，需要看其他叶子节点返回值
        return hasPathLeft || hasPathRight;
    }
}
