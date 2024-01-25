package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 21:28
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal2_推荐 {

    private int[] preorder;
    private int rootIndex = 0;
    private Map<Integer, Integer> idxMap;

    // KeyPoint 方法二 递归实现
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 前序遍历：3 9 6 10 20 15 7
        //          ↑ |左子树||右子树| => 根左右
        //        root

        // 中序遍历：6 9 10 3 15 20 7
        //         |左子树| ↑ |右子树| => 左根右
        //                root

        //      3
        //    /   \
        //   9    20
        //  / \   / \
        // 6  10 15  7

        // 注意：前序遍历和中序遍历，左右子树的个数是一定相同的

        // 递归公式
        // TreeNode root = new TreeNode(rootVal);
        // root.left = buildTree(left, mid - 1);
        // root.right = buildTree(mid + 1, right);

        // 递归终止条件
        // if (left > right) return null => 无法构建二叉树节点

        this.preorder = preorder;
        this.idxMap = new HashMap<>();
        int n = inorder.length;
        // 构建中序序列 idxMap
        // key：节点值
        // value：索引
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) return null;

        // 先序遍历次序 preorder => 依次访问根节点的次序
        // 本题中先序递归创建根节点次序，与 preorder 保持一致

        // 获取每个节点值
        int rootVal = preorder[rootIndex];
        // 遍历先序序列的指针
        rootIndex++;
        // 创建根节点
        TreeNode root = new TreeNode(rootVal);
        // mid => 根节点在中序序列中的索引
        int mid = idxMap.get(rootVal);

        // KeyPoint 通过先序遍历方式依次构建二叉树
        root.left = dfs(left, mid - 1);
        root.right = dfs(mid + 1, right);

        return root;

        // KeyPoint 手动模拟递归过程
        // 1.形参明确 [left,right]，递归过程变量值变化 rootIndex
        // 2.层次明确 dfs-1，dfs-2，dfs-3-L，dfs-3-R
        // => 方便梳理递归关系，避免思维混乱
    }
}
