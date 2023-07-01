package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 20:57
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal1 {
      /*
        103. 二叉树的锯齿形层序遍历
              给定一个二叉树，返回其节点值的锯齿形层序遍历。
              （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

                输入：
                    3      →
                   / \
                  9  20    ←
                    /  \
                   15   7  →
               输出：
                [
                    [3],
                    [20,9]
                    [15,7]
                ]
     */

    // KeyPoint 方法一 BFS
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 本质：加了一个 boolean 变量控制方向
        // 一开始从左往右
        boolean rightToLeft = false;

        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            // 创建 Integer 类型数组
            // 使用静态数组 => 任何语言都是支持，通用性好
            Integer[] levelList = new Integer[size];
            // 层次遍历过程都是从左往右遍历，添加到结果集中时需要调整次序，从而满足锯齿形遍历
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 如果是从右往左的话，那么将节点值从后往前放，否则从前往后放
                if (rightToLeft) {
                    // 1.优化 1
                    //   一开始集合使用的是 ArrayList，因为在动态数组 ArrayList 的开头添加一个元素，
                    //   该操作时间复杂度是 O(n) ，影响性能，故将其替换成静态数组 Integer[]，通过索引控制添加元素的位置
                    // 2.优化 2
                    //   Java 中使用 LinkedList 的 addFirst 和 addLast 方便调整添加的次序
                    //   详见：下面代码
                    levelList[size - 1 - i] = curr.val;
                } else {
                    levelList[i] = curr.val;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // 将静态数组转成动态数组
            res.add(Arrays.asList(levelList));
            // 遍历一层之后，调整方向
            rightToLeft = !rightToLeft;
        }
        return res;
    }

    // 将 静态数组 替换成 LinkedList，简化操作
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean rightToLeft = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (rightToLeft) {
                    levelList.addFirst(cur.val);
                } else {
                    levelList.add(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelList);
            rightToLeft = !rightToLeft;
        }
        return res;
    }
}
