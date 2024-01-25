package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-11-20 0:04
 * @Version 1.0
 */
public class _11_103_zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean rightToLeft = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (rightToLeft) {
                    list.addFirst(cur.val);
                } else {
                    list.add(cur.val);
                }

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            rightToLeft = !rightToLeft;
            res.add(list);
        }
        return res;
    }
}
