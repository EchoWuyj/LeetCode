package alg_02_train_dm._18_day_二叉树三;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 10:45
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal {
     /*
        105. 从前序与中序遍历序列构造二叉树
        根据一棵树的前序遍历与中序遍历构造二叉树。
    
        注意:
        你可以假设树中没有重复的元素。
    
        例如，给出
        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]
        返回如下的二叉树：

            3
           / \
          9  20
            /  \
           15   7
     */

    // 迭代
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int childVal = preorder[i];
            TreeNode parentNode = stack.peek();
            // TODO 需要解释说明下为什么这样判断可以？
            if (parentNode.val != inorder[inorderIndex]) {
                parentNode.left = new TreeNode(childVal);
                stack.push(parentNode.left);
            } else {
                // 找到离右子节点最近的父亲节点
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

    private int[] preorder;
    private int rootIndex = 0;
    private Map<Integer, Integer> idxMap;

    // 递归
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1);
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[rootIndex];
        rootIndex++;
        TreeNode root = new TreeNode(rootVal);
        int mid = idxMap.get(rootVal);

        root.left = buildTree(left, mid - 1);
        root.right = buildTree(mid + 1, right);

        return root;
    }
}
