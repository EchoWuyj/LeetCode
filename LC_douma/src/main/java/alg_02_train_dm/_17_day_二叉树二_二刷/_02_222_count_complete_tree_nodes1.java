package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:50
 * @Version 1.0
 */
public class _02_222_count_complete_tree_nodes1 {
      /*
          222. 完全二叉树的节点个数
            给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。

            输入：
                 1
               /   \
              2     3
             / \   /
            4   5 6
          输出： 6

          提示：
          1. 树中节点的数目范围是[0, 5 * 10^4] => 时间复杂度不能大于等于 O(n^2)
          2. 0 <= Node.val <= 5 * 10^4
          3. 题目数据保证输入的树是 完全二叉树

          进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     */

    // KeyPoint 方法一 DFS 后序遍历
    // 递归函数：返回以 root 为根节点，该树所有的节点数
    // 不单单是完全二叉树，任何树都能通过这样的方式计算
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    // 后续遍历
    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        // 返回上层根节点，计算左右子节点个数
        return left + right + 1;
    }
}
