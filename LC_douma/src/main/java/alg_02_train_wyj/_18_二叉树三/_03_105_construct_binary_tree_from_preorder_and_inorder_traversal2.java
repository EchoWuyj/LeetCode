package alg_02_train_wyj._18_二叉树三;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-13 9:29
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal2 {

    private int[] preorder;
    private int rootIndex = 0;
    private Map<Integer, Integer> idxMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        idxMap = new HashMap<>();
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) return null;
        int rootVal = preorder[rootIndex];
        rootIndex++;
        int index = idxMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);

        root.left = dfs(left, index - 1);
        root.right = dfs(index + 1, right);

        return root;
    }
}
