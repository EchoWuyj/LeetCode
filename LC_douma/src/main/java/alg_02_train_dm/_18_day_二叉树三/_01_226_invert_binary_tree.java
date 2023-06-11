package alg_02_train_dm._18_day_二叉树三;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 10:17
 * @Version 1.0
 */
// KeyPoint 详细注释 _226_InvertBinaryTree
public class _01_226_invert_binary_tree {
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

     */

    // KeyPoint 方法一:递归
    // 翻转以 root 为根的二叉树，然后返回翻转后的二叉树的根节点
    public TreeNode invertTree(TreeNode root) {
        // 递归边界考虑，空树和叶子节点不同处理方式
        // 本题可以不区分叶子节点，将叶子节点当做一般节点来处理
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            return root;
        }

        // 反转二叉树，需要有返回值，返回的是翻转后的二叉树的根节点
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    // KeyPoint 方法二：DFS 前序遍历 (迭代)
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 在前序遍历的过程中，依次翻转 curr 的左右子树
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return root;
    }

    // KeyPoint 方法三：BFS 迭代
    private TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            // 层次遍历过程中依次翻转 curr 的左右子树
            // 不用管 curr 的左右子树是否为 null，直接反转即可
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return root;

    }
}
