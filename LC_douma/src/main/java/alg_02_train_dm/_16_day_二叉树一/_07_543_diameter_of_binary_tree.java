package alg_02_train_dm._16_day_二叉树一;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 16:27
 * @Version 1.0
 */
public class _07_543_diameter_of_binary_tree {

     /* 543. 二叉树的直径
        给定一棵二叉树，你需要计算它的直径长度。
        一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
        这条路径可能穿过也可能不穿过根结点。

        输入：
              1
             / \
            2   3   穿过根结点的情况
           / \
          4   5

         输出：3

                  1
                 / \
                2   3
               / \
              4   5     没有穿过根结点的情况
             /     \
            9       7
           /         \
          6           8

          输出：6

          注意：两结点之间的路径长度是以它们之间边的数目表示。

          KeyPoint 二叉树直径 => 两个结点路径长度中最大，而路径长度是以它们之间边的数目表示
                             => 二叉树中边和顶点一一对应，统计边即为顶点，顶点转化成深度
                             => 左子树最大深度 + 右子树最大深度
                             => 为了保证最大，需要遍历每个节点，求出每个的左右子树最大深度和，再去取最大值
                                故需要后续遍历每个节点

     */

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    // KeyPoint 方法一 DFS 递归 - 后序遍历
    // 后序遍历递归函数有否有返回值 取决于 从左右子树
    // 递归返回上层时，是否需要携带左右子的信息，在 root 节点进行计算操作
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        // 递归过程中可以求到每个节点的左子树最大深度和右子树最大深度
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        // 求所有节点的直径，即左子树最大深度+右子树最大深度，在其中选择最大值
        ans = Math.max(ans, leftMaxDepth + rightMaxDepth);

        // 处理根节点
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
