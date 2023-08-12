package alg_02_train_dm._18_day_二叉树三;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 21:28
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal2 {

    private int[] preorder;
    private int rootIndex = 0;
    private Map<Integer, Integer> idxMap;

    // KeyPoint 方法二 递归
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
