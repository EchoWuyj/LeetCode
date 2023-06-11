package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 19:26
 * @Version 1.0
 */
public class _13_199_binary_tree_right_side_view {
    public List<Integer> rightSideView1(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) res.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return res;
    }

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        preOrder(root, 0, res);
        return res;
    }

    public void preOrder(TreeNode root, int level, ArrayList<Integer> res) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(root.val);
        }
        preOrder(root.right, level + 1, res);
        preOrder(root.left, level + 1, res);
    }
}
