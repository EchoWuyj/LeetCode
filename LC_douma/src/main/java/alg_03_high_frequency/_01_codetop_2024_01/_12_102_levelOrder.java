package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 23:21
 * @Version 1.0
 */
public class _12_102_levelOrder {

    // 二叉树的层序遍历
    // 迭代实现
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 特判
        if (root == null) return res;
        // Queue 结尾 ueue，Queue 是接口，使用 LinkedList 实现类
        Queue<TreeNode> queue = new LinkedList<>();
        // 加入根节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            // size 大小
            int size = queue.size();
            // 泛型：整型数值
            ArrayList<Integer> levelNodes = new ArrayList<>();
            // for 循环
            for (int i = 0; i < size; i++) {
                // 队列弹出节点，将 cur.val 加入每一层集合
                TreeNode cur = queue.poll();
                levelNodes.add(cur.val);
                // 左右节点依次进队
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelNodes);
        }
        return res;
    }
}
