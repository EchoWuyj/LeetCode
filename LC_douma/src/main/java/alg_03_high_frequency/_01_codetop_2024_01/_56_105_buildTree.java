package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 19:55
 * @Version 1.0
 */
public class _56_105_buildTree {

    private int[] preorder;
    private int[] inorder;
    private int index = 0;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        // 不要忘记初始化，主函数中统一初始化
        this.map = new HashMap<>();

        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    // 左右 left 和 right
    public TreeNode dfs(int left, int right) {
        if (left > right) return null;

        int preValue = preorder[index];
        index++;
        TreeNode root = new TreeNode(preValue);
        // 通过先序元素，确定在中序的位置索引
        int inIndex = map.get(preValue);

        root.left = dfs(left, inIndex - 1);
        root.right = dfs(inIndex + 1, right);

        return root;
    }
}
