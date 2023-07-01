package alg_02_train_dm._16_day_二叉树一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 16:27
 * @Version 1.0
 */
public class _07_543_diameter_of_binary_tree {

     /*
        543. 二叉树的直径
        给定一棵二叉树，你需要计算它的 直径长度。
        一棵二叉树的直径长度是 任意两个结点路径长度 中的最大值。
        这条路径可能穿过也可能不穿过根结点。
        注意：两结点之间的路径长度是以它们之间边的数目表示。

        输入：

              1
             / \     穿过根结点的情况
            2   3    4 -> 2 -> 1 -> 3
           / \       有 3 个边
          4   5

         输出：3

                  1
                 / \
                2   3
               / \
              4   5       没有穿过根结点的情况
             /     \      6 -> 9 -> 4 -> 2 -> 5 -> 7 -> 8
            9       7     有 6 个边
           /         \
          6           8

          输出：6


         二叉树直径：两个结点路径长度中最大，而路径长度是以它们之间边的数目表示
                     => 二叉树中，边和顶点一一对应，统计边即为顶点，顶点转化成深度
                     => 潜在直径：每个节点：左子树最大深度 + 右子树最大深度
                     => 为了保证最大，需要遍历每个节点，求出每个的节点左右子树最大深度和，
                        再从中取最大值，故需要后续遍历每个节点

     */

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    // KeyPoint 方法一 DFS 递归 - 后序遍历
    // 后序遍历，递归函数有返回值
    // => 返回值：当前节点最大深度
    // 递归返回上层时，需要携带左右子信息，在 root 节点进行计算操作
    public int maxDepth(TreeNode root) {

        // 一定将叶子节点情况考虑清楚
        // 叶子节点，即其 left 和 right 为 null
        if (root == null) return 0;

        // 每个节点，左子树最大深度，右子树最大深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        // 求所有节点的直径
        // => 每个节点：左子树最大深度+右子树最大深度，在其中选择最大值
        ans = Math.max(ans, left + right);

        // 处理当前节点
        return Math.max(left, right) + 1;
    }
}
