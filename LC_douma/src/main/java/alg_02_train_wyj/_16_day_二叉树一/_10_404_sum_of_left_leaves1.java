package alg_02_train_wyj._16_day_二叉树一;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 16:11
 * @Version 1.0
 */
public class _10_404_sum_of_left_leaves1 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (isLeafNode(cur.left)) {
                    sum += cur.left.val;
                } else {
                    queue.offer(cur.left);
                }
            }
            if (cur.right != null && !isLeafNode(cur.right)) {
                queue.offer(cur.right);
            }
        }
        return sum;
    }

    private boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
