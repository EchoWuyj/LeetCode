package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 16:15
 * @Version 1.0
 */
public class _01_226_invert_binary_tree2 {

    // KeyPoint 方法二 DFS 前序遍历 (迭代)
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 在前序遍历的过程中，依次翻转 cur 的左右子树
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            // 右子节点先入栈
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return root;
    }
}
