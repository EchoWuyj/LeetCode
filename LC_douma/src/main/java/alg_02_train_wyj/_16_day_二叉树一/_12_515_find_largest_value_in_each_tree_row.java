package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 19:12
 * @Version 1.0
 */
public class _12_515_find_largest_value_in_each_tree_row {

    public List<Integer> largestValues1(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelMax = Math.max(cur.val, levelMax);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelMax);
        }
        return res;
    }

    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        preOrder(root, 0, res);
        return res;
    }

    public void preOrder(TreeNode root, int level, ArrayList<Integer> res) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(root.val);
        } else {
            int max = Math.max(res.get(level), root.val);
            res.set(level, max);
        }
        preOrder(root.left, level + 1, res);
        preOrder(root.right, level + 1, res);
    }
}
