package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 23:21
 * @Version 1.0
 */
public class _12_102_levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        // 加入根节点
        queue.offer(root);

        while (!queue.isEmpty()) {
            // size 大小
            int size = queue.size();
            // 层次集合
            ArrayList<Integer> levelNodes = new ArrayList<>();
            // for 循环
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // cur.val 加入节点值
                levelNodes.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelNodes);
        }
        return res;
    }
}
