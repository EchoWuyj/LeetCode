package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-10 14:26
 * @Version 1.0
 */
public class _04_level_order_print {

    public static String print(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder result = new StringBuilder();
        result.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.append("null");
            }
            if (!queue.isEmpty()) {
                result.append(",");
            }
        }

        result.append("]");

        return result.toString();
    }
}
