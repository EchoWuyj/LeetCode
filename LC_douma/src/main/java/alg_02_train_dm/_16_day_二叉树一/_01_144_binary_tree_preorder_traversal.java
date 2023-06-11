package alg_02_train_dm._16_day_二叉树一;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 23:34
 * @Version 1.0
 */

// 详细注释 BinaryTree_01_PreOrder
public class _01_144_binary_tree_preorder_traversal {
    /*
        144. 二叉树的前序遍历
        给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
    */

    // 迭代解法
    public List<Integer> preorderTraversal1(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return res;
    }

    // 递归解法
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        // 处理当前遍历的节点
        res.add(node.val);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }
}
