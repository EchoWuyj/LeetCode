package alg_02_train_dm._18_day_二叉树三;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 16:16
 * @Version 1.0
 */
public class _01_226_invert_binary_tree3 {

    // KeyPoint 方法三 BFS 迭代
    private TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 层次遍历过程中依次翻转 cur 的左右子树
            // 不用管 cur 的左右子树是否为 null，直接反转即可，不能破坏原来结构
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            // 入队判空
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }

        return root;
    }
}
