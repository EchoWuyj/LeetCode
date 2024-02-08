package alg_03_high_frequency._01_codetop.top_100;

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
    // 构建中序序列 Map
    // key：节点值
    // value：位置索引
    private Map<Integer, Integer> map;

    // 从前序与中序遍历序列构造二叉树
    // 深度优先遍历
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
        // 遍历先序数组指针后移
        index++;
        TreeNode root = new TreeNode(preValue);
        // 通过先序元素，确定在中序的位置索引
        int inIndex = map.get(preValue);

        // 从 inIndex 两则进行递归调用，且不包括 index 本身
        root.left = dfs(left, inIndex - 1);
        root.right = dfs(inIndex + 1, right);

        return root;
    }
}
