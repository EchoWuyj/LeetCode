package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 23:50
 * @Version 1.0
 */

// 详细注释 BinaryTree_02_InOrder
public class _02_094_binary_tree_inorder_traversal_推荐 {
    /*
        94. 二叉树的中序遍历
            给定一个二叉树的根节点 root ，返回它的 中序 遍历。
   */

    // 迭代解法
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode pop = stack.pop();
            res.add(pop.val);
            cur = pop.right;
        }
        return res;
    }

    // 递归解法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }
}
