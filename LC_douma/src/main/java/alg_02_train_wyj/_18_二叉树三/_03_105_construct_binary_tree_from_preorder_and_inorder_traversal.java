package alg_02_train_wyj._18_二叉树三;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 20:18
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal {
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = 0;

        for (int i = 1; i < n; i++) {
            int childVal = preorder[i];
            TreeNode parentNode = stack.peek();

            if (idxMap.get(childVal) < idxMap.get(parentNode.val)) {
                parentNode.left = new TreeNode(childVal);
                stack.push(parentNode.left);
            } else {
                while (!stack.isEmpty() &&
                        inorder[inorderIndex] == stack.peek().val) {
                    parentNode = stack.pop();
                    inorderIndex++;
                }
                parentNode.right = new TreeNode(childVal);
                stack.push(parentNode.right);
            }
        }
        return root;
    }
}
