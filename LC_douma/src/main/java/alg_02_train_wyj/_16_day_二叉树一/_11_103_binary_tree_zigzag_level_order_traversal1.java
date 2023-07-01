package alg_02_train_wyj._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 17:07
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal1 {
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean rightToLeft = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer[] levelList = new Integer[size];
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (rightToLeft) {
                    levelList[size - 1 - i] = cur.val;
                } else {
                    levelList[i] = cur.val;
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(Arrays.asList(levelList));
            rightToLeft = !rightToLeft;
        }
        return res;
    }
}


