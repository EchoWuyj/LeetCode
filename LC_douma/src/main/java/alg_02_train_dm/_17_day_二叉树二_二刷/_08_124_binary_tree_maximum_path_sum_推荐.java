package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:54
 * @Version 1.0
 */
public class _08_124_binary_tree_maximum_path_sum_推荐 {

    /*
        124. 二叉树中的最大路径和
        路径：被定义为一条从树中 任意节点 出发，沿父节点-子节点(子节点 - 沿父节点 也可以)连接，
        达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
        该路径 至少包含一个 节点，且不一定经过根节点。

        路径和：是路径中各节点值的总和。
        给你一个二叉树的根节点 root ，返回其 最大路径和

           -10
          /   \
         9     20
               / \
              15  7

        输入：root = [-10,9,20,null,null,15,7]
        输出：42
        解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

        提示：
        树中节点数目范围是 [1, 3 * 10^4]
        -1000 <= Node.val <= 1000
  */

    // KeyPoint 涉及比较，必须进行初始化，否则无法比较
    // 因为是求最大值，所以初始化为最小值
    // Integer.MIN_VALUE => -2147483648，递归方法没有调用，导致返回的是 -2147483648
    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        //     11 ← 20
        //   /   \
        //  7     2

        //     11 ← 18
        //   /   \
        //  7     -2  ← 负数，贡献值为 0

        // 注意：这里左右子树，求的不是最大路径，而是求最大路径的最大贡献值

        dfs(root);
        return maxPathSum;
    }

    // 函数功能：返回以 node 为根节点，左右子树中的最大贡献值
    //          而不是返回以 node 为根节点的最大路径和
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // 递归计算左右子节点的最大贡献值
        // 因为二叉树中节点值可能存在负值，所以需要在外面包裹一层 Math.max
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        // 每个节点都有最大路径和，都有可能更新全局最大路径和 maxPathSum
        // KeyPoint 注意事项
        // 1.最大路径和是在回溯中计算出来的，而不是作为递归函数的返回值直接返回的
        // 2.路径可以从左子树，经过根节点，到右子树 => left 和 right 可以都选
        //   左最大贡献值 + 右最大贡献值 + root值 => 最大路径和
        // 3.区别：计算最大路径和 和 计算最大贡献值
        maxPathSum = Math.max(maxPathSum, left + right + node.val);

        // 该以 node 为根节点的最大贡献值
        // => 最大贡献只是考虑一条路径，left 和 right 中选择一个，只能是单侧
        // => Math.max(left, right) + node.val
        //    不能这样 left + right + node.val
        return Math.max(left, right) + node.val;

        //         ← 最大贡献值：21
        //      8  ← 最大路径和：13+8+5=26
        //    /   \
        //   13    4 ← 最大贡献值：5
        //         / \ 最大路径和：4+(-5)+1=0
        //        -5  1

    }
}
