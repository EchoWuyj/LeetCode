package alg_02_train_dm._18_day_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 10:17
 * @Version 1.0
 */

public class _01_226_invert_binary_tree1 {
     /*
        226. 翻转二叉树
        翻转一棵二叉树

        示例：
        输入：
             4
           /   \
          2     7
         / \   / \
        1   3 6   9
        输出：
             4
           /   \
          7     2
         / \   / \
        9   6 3   1

        提示：

        树中节点数目范围在 [0, 100] 内
        -100 <= Node.val <= 100

     */

    // KeyPoint 方法一 递归
    // 递归含义：翻转以 root 为根的二叉树，然后返回翻转后的二叉树的根节点
    public TreeNode invertTree(TreeNode root) {

        // 递归边界考虑：空树和叶子节点不同处理方式
        // 1.空节点
        if (root == null) return null;
        // 2.叶子节点
        if (root.left == null && root.right == null) {
            return root;
        }

        // KeyPoint 优化
        // 本题可以不区分空节点和叶子节点，将叶子节点当做一般节点来处理
        // 但是别的题目可能需要区分，学习这种处理方式

        // 反转二叉树，需要有返回值，返回的是翻转后的二叉树的根节点
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 注意翻转当前的节点操作的位置 => 后续遍历进行的操作
        // 在左右子树都反转好之后，再去进行对已经反转好的左右子树，再去反转
        root.left = right;
        root.right = left;
        return root;
    }
}
