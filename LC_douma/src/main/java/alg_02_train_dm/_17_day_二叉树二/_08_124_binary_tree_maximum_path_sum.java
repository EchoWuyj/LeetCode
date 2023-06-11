package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:54
 * @Version 1.0
 */
public class _08_124_binary_tree_maximum_path_sum {
    /* 124. 二叉树中的最大路径和

     路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
     同一个节点在一条路径序列中 至多出现一次 。
     该路径 至少包含一个 节点，且不一定经过根节点。
     路径和 是路径中各节点值的总和。
     给你一个二叉树的根节点 root ，返回其 最大路径和 。

     提示：
     树中节点数目范围是 [1, 3 * 10^4]
     -1000 <= Node.val <= 1000
  */

    // bug 修复：因为是求最大值，所以初始化为最小值
    // 因为涉及比较，必须进行初始化，否则无法比较
    // Integer.MIN_VALUE => -2147483648，递归方法没有调用，导致返回的是 -2147483648
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // 返回以 node 为根节点，左右子树中的最大贡献值
    // 而不是:返回以 node 为根节点的最大路径和
    // 最大路径和是在回溯中计算出来的，而不是作为递归函数的返回值直接返回的
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // 递归计算左右子节点的最大贡献值
        // 因为二叉树中节点值存在负值，所以需要在外面包裹一层 Math.max
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 更新 maxSum，左右子树最大贡献值已知，加上 node 自身值，可得最大路径和
        // 左最大贡献值 + 右最大贡献值 + root值 => 最大路径和
        maxSum = Math.max(maxSum, leftGain + rightGain + node.val);

        // 左右中最大贡献值 + node.val => 该以 node 为根节点的最大贡献
        return Math.max(leftGain, rightGain) + node.val;
    }
}
