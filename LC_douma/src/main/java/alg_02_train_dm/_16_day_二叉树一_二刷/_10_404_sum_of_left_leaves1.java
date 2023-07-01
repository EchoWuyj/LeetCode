package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 20:19
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves1 {
     /*
        404. 左叶子之和
       计算给定二叉树的所有左叶子之和。

        示例：
            3
           / \
          9  20
            /  \
           15   7

        输出：24

        提示:
        节点数在 [1, 1000] 范围内
        -1000 <= Node.val <= 1000
     */

    // KeyPoint 方法一 BFS
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 注意：
            // 一般不要将嵌套的 if 判断逻辑合并，因为有 if 就会有 else，若将 if 合并，则判断逻辑可能存在问题
            // 比如：外侧 if (node.left != null) 和 内侧 if (isLeafNode(node.left))
            // => 若合并 if (node.left != null && isLeafNode(node.left))
            // => 整体 if 对应 else 逻辑： node.left == null || !isLeafNode(node.left)
            //    此时 else 附加了 !isLeafNode(node.left) 判断逻辑

            if (node.left != null) {
                // 判断 node.left 是否是叶子节点
                // 若 node.left 是叶子节点，sum 累和，且不用加入队列了
                if (isLeafNode(node.left)) {
                    sum += node.left.val;
                } else {
                    // 若 node.left 不是叶子节点，加入队列
                    queue.offer(node.left);
                }
            }
            // node.right 不为 null，且不是 右叶子节点 才进入队列
            // 否则不用进入队列，因为右叶子节点已经没有左右孩子了，一点小优化
            if (node.right != null && !isLeafNode(node.right))
                queue.offer(node.right);
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
