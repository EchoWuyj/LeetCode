package alg_01_ds_dm._02_tree._01_bt.train;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 21:15
 * @Version 1.0
 */
// 翻转二叉树
public class _226_InvertBinaryTree {

    // KeyPoint 方法一:DFS(迭代)
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

    // KeyPoint 方法二:递归
    public TreeNode invertTree2(TreeNode root) {
        // 递归边界，不需要返回什么，直接返回 null;
        if (root == null) return null;
        // 递归调用翻转左子树，并返回左子树的根节点
        TreeNode left = invertTree2(root.left);
        // 递归调用翻转右子树，并返回右子树的根节点
        TreeNode right = invertTree2(root.right);

        // 注意递归操作的位置，类似于后续遍历中操作
        // 在左右子树都反转好之后，再去进行对已经反转好的左右子树再去反转

        // 翻转当前的节点
        root.left = right;
        root.right = left;
        return root;
    }

    // KeyPoint 方法二: BFS 迭代
    private TreeNode invertTree(TreeNode root) {
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
