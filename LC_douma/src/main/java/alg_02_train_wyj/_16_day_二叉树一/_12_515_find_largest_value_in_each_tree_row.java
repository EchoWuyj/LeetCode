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
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                maxValue = Math.max(maxValue, cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(maxValue);
        }
        return res;
    }

    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode node, int level, List<Integer> res) {
        if (node == null) return;

        if (res.size() == 0) {
            res.add(node.val);
        } else {
            int maxValue = Math.max(node.val, res.get(level));
            res.set(level, maxValue);
        }

        dfs(node.left, level + 1, res);
        dfs(node.right, level + 1, res);
    }
}
