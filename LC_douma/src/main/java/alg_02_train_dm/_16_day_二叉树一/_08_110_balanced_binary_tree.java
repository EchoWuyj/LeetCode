package alg_02_train_dm._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 17:56
 * @Version 1.0
 */
//
public class _08_110_balanced_binary_tree {
      /*  110. 平衡二叉树
            给定一个二叉树，判断它是否是高度平衡的二叉树。
            本题中，一棵高度平衡二叉树定义为：
            一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。

        输入：
              1
             / \
            2   3
           / \
          4   5
        输出：true

        输入：
              1
             / \
            2   3
           / \
          4   5
         / \
        6   7
        输出：false
     */

    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) >= 0;
    }

    // KeyPoint 方法一 DFS 递归 - 后序遍历
    // 在求二叉树最大深度时，判断左右子树最大深度的相差的绝对值是否超过 1
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        // 高度和深度是一个意思，数值上是相等的，只是判断的方向不同
        // 高度:从下往上 ↑
        // 深度:从上往下 ↓
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        // KeyPoint 计算之前，先判断，判断不成立，不用计算

        // 判断是否平衡
        // 左子树最大深度，右子树最大深度，若其中有一个为 -1，则是不平衡的
        if (leftMaxDepth == -1 || rightMaxDepth == -1) {
            return -1;
        }

        // 计算是否不平衡
        if (Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
            return -1;
        }

        // 如果树是平衡的，则返回树最大深度
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
