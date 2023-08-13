package alg_02_train_wyj._18_二叉树三;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-13 9:41
 * @Version 1.0
 */
public class _04_106_construct_binary_tree_from_inorder_and_postorder_traversal {

    private int[] postOrder;
    private int rootIndex;
    private Map<Integer, Integer> idxMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postOrder = postorder;
        idxMap = new HashMap<>();
        int n = postorder.length;
        rootIndex = n - 1;
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) return null;
        int rootVal = postOrder[rootIndex];
        rootIndex--;
        TreeNode root = new TreeNode(rootVal);
        int mid = idxMap.get(rootVal);
        root.right = dfs(mid + 1, right);
        root.left = dfs(left, mid - 1);
        return root;
    }
}
