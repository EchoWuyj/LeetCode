package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:58
 * @Version 1.0
 */
public class _21_103_zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 必须进行判空，否则本题存在空指针异常
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 一般都是从左往右，故将 rightToLeft 设置成 false
        // 右到左，更喜欢右边
        boolean rightToLeft = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 头插法
                if (rightToLeft) {
                    levelList.addFirst(cur.val);
                } else {
                    // 正常添加元素
                    levelList.add(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelList);
            // 改变方向
            rightToLeft = !rightToLeft;
        }
        return res;
    }
}
