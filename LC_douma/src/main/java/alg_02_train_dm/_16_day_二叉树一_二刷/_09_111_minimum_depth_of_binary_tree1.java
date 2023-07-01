package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 19:21
 * @Version 1.0
 */
public class _09_111_minimum_depth_of_binary_tree1 {
     /*
        111. 二叉树的最小深度
        给定一个二叉树，找出其最小深度。
        最小深度是从 根节点 到 最近叶子节点 的最短路径上的节点数量。
        说明：叶子节点是指没有子节点的节点。

        输入：
              1
             / \
            2   3
           / \
          4   5

         输出：2

        输入：
              1
             / \
            2  NULL -> 这不是叶子节点，而是空节点
           /         -> 叶子节点的左右子树都为 null    3
          4                                         / \
         /                                       null null
        5
       /
      6
         输出： 5
     */

    //  遇到几乎所有的二叉树问题都想到将其转成二叉树遍历的问题
    //  => 两个类 DFS 和 BFS

    // KeyPoint 方法一 BFS
    // BFS 可以计算二叉树最大深度，有几层则深度为几，类比求解最小深度
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
//        int levels = 1;
        int levels = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 将 levels++ 上移，避免 root 为叶子节点，返回 0
            // 也可以将 levels 初值设置为 1，刚开始就将 root 算上
            levels++;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 层次遍历中，遇到第一个叶子节点，直接返回 levels 即可
                if (curr.left == null && curr.right == null) {
                    return levels;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return levels;
    }
}
