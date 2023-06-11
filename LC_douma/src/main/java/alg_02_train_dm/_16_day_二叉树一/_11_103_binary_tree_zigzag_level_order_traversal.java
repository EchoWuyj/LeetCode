package alg_02_train_dm._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 20:57
 * @Version 1.0
 */
public class _11_103_binary_tree_zigzag_level_order_traversal {
      /* 103. 二叉树的锯齿形层序遍历
              给定一个二叉树，返回其节点值的锯齿形层序遍历。
              （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

                输入：
                    3
                   / \
                  9  20
                    /  \
                   15   7
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
        // 一开始从左往右
        boolean fromRight = false;
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            // 使用静态数组 => 任何语言都是支持，通用性好
            Integer[] levelNodes = new Integer[size];
            // 层次遍历过程都是从左往右遍历，添加到结果集中时需要调整次序，从而满足锯齿形遍历
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 如果是从右往左的话，那么将节点值从后往前放，否则从前往后放
                if (fromRight) {
                    // 避免在动态数组 ArrayList 的开头添加一个元素，因为是 O(n) 的操作，影响性能
                    // 优化:Java 中使用 LinkedList 的 addFirst 和 addLast 方便调整添加的次序
                    levelNodes[size - 1 - i] = curr.val;
                } else {
                    levelNodes[i] = curr.val;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // 将静态数组转成动态数组
            res.add(Arrays.asList(levelNodes));
            // 遍历一层之后，调整方向
            fromRight = !fromRight;
        }

        return res;
    }

    // KeyPoint 方法二 DFS 前序遍历(递归)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        preorder(root, 0, res);
        return res;
    }

    private void preorder(TreeNode node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;

        // KeyPoint 前序遍历 => 处理当前遍历节点逻辑在递归之前

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            List<Integer> levelNodes = new LinkedList<>();
            res.add(levelNodes);
        }

        // KeyPoint 获取当前层的结果集
//        List<Integer> levelNodes = res.get(currLevel);
        // 返回父类引用 List，无法调用 LinkedList 对象的方法，需要向下强转
        // 或者创建对象时，显示申明 LinkedList<Integer> levelNodes = new LinkedList<>();

        // Raw use of parameterized class 需要加上 <Integer>
        // 必须生成变量形式，(LinkedList<Integer>) res.get(currLevel).addFirst 不行
        LinkedList<Integer> levelNodes = (LinkedList<Integer>) res.get(currLevel);

        // KeyPoint 最简单方式 List<List<Integer>> res => List<LinkedList<Integer>> res

        // currLevel 是从 0 开始的，故从左往右添加
        if (currLevel % 2 == 0) levelNodes.add(node.val);
        else levelNodes.addFirst(node.val);

        preorder(node.left, currLevel + 1, res);
        preorder(node.right, currLevel + 1, res);
    }
}
