package alg_02_train_dm._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 20:19
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves {
     /* 404. 左叶子之和
       计算给定二叉树的所有左叶子之和。

        示例：
            3
           / \
          9  20
            /  \
           15   7

        输出：24
     */

    // KeyPoint 方法一 BFS
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // KeyPoint 将多个 if 逻辑合并，若不涉及 else 则可以，若有 else 则判断逻辑可能存在问题
            // if (node.left != null && isLeafNode(node.left)) 此时 else 附加了 node.left 判断逻辑
            if (node.left != null) {
                // 判断 node.left 是否是叶子节点
                if (isLeafNode(node.left)) {
                    sum += node.left.val;
                    // node.left 都已经是叶子节点了，就不需要再去进入队列了
                } else {
                    queue.offer(node.left);
                }
            }
            // 不是右叶子节点才进入队列，否则不用进入队列，因为右叶子节点已经没有左右孩子了
            if (node.right != null && !isLeafNode(node.right))
                queue.offer(node.right);
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // KeyPoint 方法二 DFS 前序遍历
    private int sum = 0;

    public int sumOfLeftLeaves1(TreeNode root) {
        // root 没有父节点，将 root 自己作为父节点
        preorder(root, root);
        return sum;
    }

    // KeyPoint 递归过程需要额外信息:当前节点和父亲节点，即通过形参传入
    private void preorder(TreeNode node, TreeNode parent) {
        if (node == null) return;

        // 处理当前节点 => 保证该节点是叶子节点，同时是 parent 的左子节点
        if (node.left == null &&
                node.right == null &&
                parent.left == node) { // 左叶子
            sum += node.val;
        }

        preorder(node.left, node);
        preorder(node.right, node);
    }

    // KeyPoint 方法三 DFS 后序遍历
    public int sumOfLeftLeaves2(TreeNode root) {
        return postorder(root, root);
    }

    // 1 有返回值
    //   1.1 返回值 int 是以 node 为根节点的子树所有的左叶子之和
    //   1.2 先计算左右子节点的值，再返回根节点的值，本质就是后序遍历
    private int postorder(TreeNode node, TreeNode parent) {
        // 1 空树的左叶子和为 0，直接返回
        if (node == null) return 0;
        // 2 左叶子
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            // 返回给递归上层进行计算
            return node.val;
        }

        int leftLeavesSum = postorder(node.left, node);
        int rightLeavesSum = postorder(node.right, node);

        // 处理当前根节点
        return leftLeavesSum + rightLeavesSum;
    }

    // 2 不返回值
    //   2.1 需要使用全局变量 sum ，记录遍历过程中进行计算操作的值，遍历完所有节点值也计算好了
    private void postorder1(TreeNode node, TreeNode parent) {
        if (node == null) return;
        postorder1(node.left, node);
        postorder1(node.right, node);

        // 处理当前节点
        if (node.left == null &&
                node.right == null &&
                parent.left == node) {
            sum += node.val;
        }
    }
}
