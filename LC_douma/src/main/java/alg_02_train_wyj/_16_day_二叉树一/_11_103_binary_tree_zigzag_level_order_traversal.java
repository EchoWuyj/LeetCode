package alg_02_train_wyj._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 17:07
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal {
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean fromLeft = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelRes = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (fromLeft) {
                    levelRes.add(cur.val);
                } else {
                    levelRes.addFirst(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            fromLeft = !fromLeft;
            res.add(levelRes);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        preOrder(root, 0, res);
        return res;
    }

    public void preOrder(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() == level) {
            LinkedList<Integer> levelRes = new LinkedList<>();
            res.add(levelRes);
        }

        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            LinkedList<Integer> list = (LinkedList<Integer>) res.get(level);
            list.addFirst(root.val);
        }

        preOrder(root.left, level + 1, res);
        preOrder(root.right, level + 1, res);
    }
}
