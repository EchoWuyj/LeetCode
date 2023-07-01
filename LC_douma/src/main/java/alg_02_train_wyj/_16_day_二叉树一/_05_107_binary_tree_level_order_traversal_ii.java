package alg_02_train_wyj._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 13:20
 * @Version 1.0
 */
public class _05_107_binary_tree_level_order_traversal_ii {
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelList.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.addFirst(levelList);
        }
        return res;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        preOrder(root, 0, res);
        Collections.reverse(res);
        return res;
    }

    public void preOrder(TreeNode root, int level, LinkedList<List<Integer>> res) {
        if (root == null) return;
        if (res.size() == level) {
            ArrayList<Integer> levelRes = new ArrayList<>();
            levelRes.add(root.val);
            res.add(levelRes);
        } else {
            res.get(level).add(root.val);
        }
        preOrder(root.left, level + 1, res);
        preOrder(root.right, level + 1, res);
    }
}
