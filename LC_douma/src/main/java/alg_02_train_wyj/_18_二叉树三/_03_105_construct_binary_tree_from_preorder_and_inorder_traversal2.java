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
    private int index;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        index = 0;
        map = new HashMap<>();

        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    public TreeNode dfs(int left, int right) {
        if (left > right) return null;
        int rootValue = preorder[index];
        index++;
        int rootIndex = map.get(rootValue);
        TreeNode rootNode = new TreeNode(rootValue);

        rootNode.left = dfs(left, rootIndex - 1);
        rootNode.right = dfs(rootIndex + 1, right);
        return rootNode;
    }
}


