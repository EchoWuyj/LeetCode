package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 15:30
 * @Version 1.0
 */

// KeyPoint 详细注释 104_MaximumDepthOfBinaryTree
public class _06_104_maximum_depth_of_binary_tree1 {

      /*
       104. 二叉树的最大深度
       给定一个二叉树，找出其最大深度。
       二叉树的深度为:根节点到最远叶子节点的最长路径上的节点数。
       说明: 叶子节点是指没有子节点的节点。

       输入：
            3
           / \
          9  20
            /  \
           15   7
       输出：3

       KeyPoint 叶子节点的 Depth 数值上等价于从根节点到叶子节点沿途的节点个数

     */

    // KeyPoint 方法一 BFS
    // 针对具体题目，结合 BFS 代码模板，二次修改，从而将本题解决
    // 面对一个新的问题，一定是结合之前已经做过的题目(代码)来思考
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 注意定义变量 levels 位置
        // 若放在 while 循环里面，while 结束 levels 就释放了
        //故需要将 levels 定义在 while 的外面
        int levels = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            levels++;
        }
        return levels;
    }
}
